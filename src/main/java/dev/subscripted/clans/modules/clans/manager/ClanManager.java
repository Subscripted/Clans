package dev.subscripted.clans.modules.clans.manager;

import dev.subscripted.clans.modules.clans.pojo.Clan;
import dev.subscripted.clans.modules.database.MySQL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ClanManager {


    @SneakyThrows
    public void createClan(String clanName, String clanTag, UUID ownerUUID) {
        try (Connection connection = MySQL.getConnection().get();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Clans (Clanname, Clantag, OwnerUUID, Members) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, clanName);
            preparedStatement.setString(2, clanTag);
            preparedStatement.setString(3, ownerUUID.toString());
            preparedStatement.setString(4, ownerUUID.toString());
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public void addClanMember(String clanName, UUID memberUUID) {
        try (Connection connection = MySQL.getConnection().get();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Clans SET Members = CONCAT(Members, ?) WHERE Clanname = ?")) {

            preparedStatement.setString(1, "," + memberUUID.toString());
            preparedStatement.setString(2, clanName);
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public Clan getClan(String clanName) {
        try (Connection connection = MySQL.getConnection().get();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Clans WHERE Clanname = ?")) {

            preparedStatement.setString(1, clanName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String retrievedClanName = resultSet.getString("Clanname");
                    String clanTag = resultSet.getString("Clantag");
                    UUID ownerUUID = UUID.fromString(resultSet.getString("OwnerUUID"));


                    return new Clan(retrievedClanName, clanTag, ownerUUID);
                }
            }
        }

        return null;
    }

    @SneakyThrows
    public List<UUID> getClanMembers(String clanName) {
        List<UUID> members = new ArrayList<>();

        try (Connection connection = MySQL.getConnection().get();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT Members FROM Clans WHERE Clanname = ?")) {

            preparedStatement.setString(1, clanName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String membersString = resultSet.getString("Members");
                    String[] membersArray = membersString.split(",");

                    for (String memberUUID : membersArray) {
                        members.add(UUID.fromString(memberUUID));
                    }
                }
            }
        }

        return members;
    }

    @SneakyThrows
    public void removeClanMember(String clanName, UUID memberUUID) {
        try (Connection connection = MySQL.getConnection().get();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Clans SET Members = REPLACE(Members, ?, '') WHERE Clanname = ?")) {

            preparedStatement.setString(1, memberUUID.toString());
            preparedStatement.setString(2, clanName);
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public void deleteClan(String clanName) {
        try (Connection connection = MySQL.getConnection().get();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Clans WHERE Clanname = ?")) {

            preparedStatement.setString(1, clanName);
            preparedStatement.executeUpdate();
        }
    }
}

