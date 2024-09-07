package com.clipboar.repository;

import com.clipboar.domain.clipboard.Clipboard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClipboardRepository extends MongoRepository<Clipboard, String> {

    List<Clipboard> findBySettingsNetworkCode(String networkCode);

    Clipboard findBySharingCode(String sharingCode);

}
