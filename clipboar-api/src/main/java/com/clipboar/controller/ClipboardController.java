package com.clipboar.controller;

import com.clipboar.domain.clipboard.Clipboard;
import com.clipboar.domain.clipboard.ClipboardDTO;
import com.clipboar.service.ClipboardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clipboard")
public class ClipboardController {

    private final ClipboardService clipboardService;

    public ClipboardController(ClipboardService clipboardService) {
        this.clipboardService = clipboardService;
    }

    @PostMapping
    public ResponseEntity<Clipboard> create(@RequestBody ClipboardDTO clipboardData, HttpServletRequest request) {
        String clientIpAddress = null;

        if(clipboardData.settings().isNetworkVisible()){
            clientIpAddress = getClientIpAddress(request);
        }

        Clipboard newClipboard = this.clipboardService.create(clipboardData, clientIpAddress);
        return ResponseEntity.ok().body(newClipboard);
    }

    @GetMapping("/network")
    public ResponseEntity<List<Clipboard>> getByNetwork(HttpServletRequest request){
        List<Clipboard> clipboardList = this.clipboardService.readByNetwork(getClientIpAddress(request));
        return ResponseEntity.ok().body(clipboardList);
    }

    @GetMapping("/sharingCode/{id}")
    public ResponseEntity<String> getBySharingCode(@PathVariable("id") String id){
        String clipboardContent = this.clipboardService.readBySharingCode(id);
        return ResponseEntity.ok().body(clipboardContent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clipboard> update(@PathVariable("id") String id, @RequestBody ClipboardDTO clipboardData){
        Clipboard clipboard = this.clipboardService.update(id, clipboardData);
        return ResponseEntity.ok().body(clipboard);
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }

}
