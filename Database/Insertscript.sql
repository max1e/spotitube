USE Spotitube;

INSERT INTO HTTPExceptions (statusCode, exceptionName)
VALUES (401, "Unauthorized"),
	   (402, "Forbidden"),
       (1, "Invalid credentials");

INSERT INTO Users (username, hashedPassword, firstName, lastName, token)
VALUES ("plakplaatje", "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8", "Max", "Neerken", null),
	   ("jelmo", "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8", "Jelmer", "van Vugt", null);

INSERT INTO Performers (performerName) 
VALUES ("TestPerformer1"),
	   ("TestPerformer2"),
	   ("TestPerformer3"),
	   ("TestPerformer4"),
	   ("TestPerformer5"),
	   ("TestPerformer6");
       
INSERT INTO Albums (albumName)
VALUES ("TestAlbum1"),
	   ("TestAlbum2"),
	   ("TestAlbum3"),
	   ("TestAlbum4"),
	   ("TestAlbum5"),
	   ("TestAlbum6");

INSERT INTO Tracks (title, performer, duration, album, playcount, publicationDate, trackDescription, offlineAvailable) 
VALUES ("TestTitle1", 1, 1, 1, 11, "2010-10-10", "TestDescription1", true),
	   ("TestTitle2", 2, 2, 2, 22, "2010-10-10", "TestDescription2", true),
	   ("TestTitle3", 3, 3, 3, 33, "2010-10-10", "TestDescription3", true),
	   ("TestTitle4", 4, 4, 4, 44, "2010-10-10", "TestDescription4", true),
	   ("TestTitle5", 5, 5, 5, 55, "2010-10-10", "TestDescription5", true),
	   ("TestTitle6", 6, 6, 6, 66, "2010-10-10", "TestDescription6", true);

INSERT INTO Playlists (playlistName, playlistOwner) 
VALUES ("Playlist1", 1),
	   ("Playlist2", 2);

INSERT INTO PlaylistsTracks(playlistId, trackId) 
VALUES (1, 1),
	   (1, 2),
	   (1, 3),
	   (2, 4),
	   (2, 5),
	   (2, 6);