package dev.subscripted.clans.modules.clans.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Clan {

    private String clanName;
    private String clanTag;
    private UUID ownerUUID;

    public Clan(String clanName, String clanTag) {
        this(clanName, clanTag, null);
    }
}
