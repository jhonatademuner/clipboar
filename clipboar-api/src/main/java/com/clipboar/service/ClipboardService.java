package com.clipboar.service;

import com.clipboar.domain.clipboard.Clipboard;
import com.clipboar.domain.clipboard.ClipboardDTO;
import com.clipboar.domain.clipboard.exception.ClipboardNotFoundException;
import com.clipboar.repository.ClipboardRepository;
import com.clipboar.util.aes.AESUtil;
import com.clipboar.util.aes.exception.AesDecryptException;
import com.clipboar.util.aes.exception.AesEncryptException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ClipboardService {

    private final ClipboardRepository clipboardRepository;

    public ClipboardService(ClipboardRepository clipboardRepository) {
        this.clipboardRepository = clipboardRepository;
    }

    public Clipboard create(ClipboardDTO clipboardData, String clientIpAddress) {
        Clipboard newClipboard = new Clipboard(clipboardData);

        String sharingCode = generateCode();

        try {
            String encryptedContent = AESUtil.encrypt(clipboardData.content(), AESUtil.repeatToLength(sharingCode, 16));
            newClipboard.setContent(encryptedContent);
            if(clipboardData.settings().isNetworkVisible()){
                String encryptedNetworkCode = AESUtil.encrypt(clientIpAddress, AESUtil.repeatToLength(clientIpAddress, 16));
                newClipboard.getSettings().setNetworkCode(encryptedNetworkCode);
            }
        } catch (Exception e) {
            throw new AesEncryptException("Encrypt error on Clipboard with Sharing code: " + sharingCode, e);
        }

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MILLISECOND, clipboardData.settings().getExpirationTime());
        Date futureDate = calendar.getTime();

        newClipboard.setExpiresAfter(futureDate);
        newClipboard.setSharingCode(sharingCode);
        newClipboard.setCreatedDate(new Date());
        newClipboard.setLastModifiedDate(new Date());
        this.clipboardRepository.save(newClipboard);

        return newClipboard;
    }


    public List<Clipboard> readByNetwork(String clientIpAddress){

        String encryptedClientIpAddress;
        try {
            encryptedClientIpAddress = AESUtil.encrypt(clientIpAddress, AESUtil.repeatToLength(clientIpAddress, 16));
        } catch (Exception e) {
            throw new AesEncryptException("Error while encrypting clientIpAddress:", e);
        }

        List<Clipboard> clipboardList = this.clipboardRepository.findBySettingsNetworkCode(encryptedClientIpAddress);

        for(Clipboard clipboard : clipboardList){
            String sharingCode = clipboard.getSharingCode();
            try {
                clipboard.setContent(AESUtil.decrypt(clipboard.getContent(), AESUtil.repeatToLength(sharingCode, 16)));
            } catch (Exception e) {
                throw new AesDecryptException("Decrypt error on Clipboard with Sharing code: " + sharingCode, e);
            }
        }

        return clipboardList;
    }

    public String readBySharingCode(String id){
        Clipboard clipboard = this.clipboardRepository.findBySharingCode(id);
        String sharingCode = clipboard.getSharingCode();
        try{
            return AESUtil.decrypt(clipboard.getContent(), AESUtil.repeatToLength(sharingCode, 16));
        } catch (Exception e){
            throw new AesDecryptException("Decrypt error on Clipboard with Sharing code: " + sharingCode, e);
        }
    }

    public Clipboard update(String id, ClipboardDTO clipboardData){
        Clipboard clipboard = this.clipboardRepository.findById(id).orElseThrow(ClipboardNotFoundException::new);
        if(!clipboardData.content().isEmpty()) clipboard.setContent(clipboardData.content());
        if(clipboardData.settings() != null) clipboard.setSettings(clipboardData.settings());
        if(!clipboardData.sharingCode().isEmpty()) clipboard.setSharingCode(clipboardData.sharingCode());
        clipboard.setLastModifiedDate(new Date());
        this.clipboardRepository.save(clipboard);
        return clipboard;
    }

    private String generateCode() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, Math.min(uuid.length(), 6));
    }

}
