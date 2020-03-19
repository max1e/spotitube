/**
 * Spotitube database DDL
 * Creates database schema and tables for the spotitube application
 * Max Neerken - 16-3-2020
 */

DROP SCHEMA IF EXISTS Spotitube;
CREATE SCHEMA Spotitube;
USE Spotitube;

CREATE TABLE Users (
	userId int auto_increment not null,
	username varchar(255) not null,
    hashedPassword char(64) not null,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    token char(14) null,
    CONSTRAINT pk_Users PRIMARY KEY (userId),
    CONSTRAINT ak_Users_username UNIQUE (username),
    CONSTRAINT ak_Users_token UNIQUE (token)
);

CREATE TABLE Performers (
	performerId int auto_increment not null,
    performerName varchar(255) not null,
    CONSTRAINT pk_Performers PRIMARY KEY (performerId),
    CONSTRAINT ak_Performers UNIQUE (performerName)
);

CREATE TABLE Albums (
	albumId int auto_increment not null,
    albumName varchar(255) not null,
    CONSTRAINT pk_Albums PRIMARY KEY (albumId)
);

CREATE TABLE Tracks (
	trackId int auto_increment not null,
    title varchar(255) not null,
    performer int not null,
    duration int not null,
    album int null,
    playcount int null,
    publicationDate date null,
    trackDescription varchar(255) null,
    offlineAvailable boolean not null,
    CONSTRAINT pk_Tracks PRIMARY KEY (trackId),
    CONSTRAINT fk_Tracks_Perfomers FOREIGN KEY (performer)
		REFERENCES Performers(performerId)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT fk_Tracks_Albums FOREIGN KEY (album)
		REFERENCES Albums(albumId)
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE Playlists (
	playlistId int auto_increment not null,
	playlistName varchar(255) not null,
    playlistOwner int not null,
    CONSTRAINT pk_Playlists PRIMARY KEY (playlistId),
    CONSTRAINT fk_Playlist_User FOREIGN KEY (playlistOwner)
		REFERENCES Users(userId)
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE PlaylistsTracks (
	playlistId int not null,
    trackId int not null,
    CONSTRAINT pk_PlaylistsTracks PRIMARY KEY (playlistId, trackId),
    CONSTRAINT fk_PlaylistsTracks_Playlists FOREIGN KEY (playlistId)
		REFERENCES Playlists(playlistId)
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
	CONSTRAINT fk_PlaylistsTracks_Tracks FOREIGN KEY (trackId)
		REFERENCES Tracks(trackId)
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);