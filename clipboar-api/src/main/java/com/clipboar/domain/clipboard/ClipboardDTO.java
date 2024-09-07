package com.clipboar.domain.clipboard;

import com.clipboar.domain.clipboard.settings.ClipboardSettings;

import java.util.Date;

public record ClipboardDTO(
    String id,
    String content,
    ClipboardSettings settings,
    String sharingCode,
    Date createdDate,
    Date lastModifiedDate,
    Date expiresAfter
) {}
