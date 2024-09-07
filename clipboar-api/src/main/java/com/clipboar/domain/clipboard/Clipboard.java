package com.clipboar.domain.clipboard;

import com.clipboar.domain.clipboard.settings.ClipboardSettings;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "clipboards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clipboard {

    @Id
    public String id;
    private String content;
    private ClipboardSettings settings;
    private String sharingCode;
    private Date createdDate;
    private Date lastModifiedDate;
    private Date expiresAfter;

    public Clipboard (ClipboardDTO clipboardDTO){
        this.id = clipboardDTO.id();
        this.content = clipboardDTO.content();
        this.settings = clipboardDTO.settings();
        this.sharingCode = clipboardDTO.sharingCode();
        this.createdDate = clipboardDTO.createdDate();
        this.lastModifiedDate = clipboardDTO.lastModifiedDate();
        this.expiresAfter = clipboardDTO.expiresAfter();
    }

}
