#IRC Bot Testing SQL Queries
INSERT INTO Type(Name)
VALUES('Spotify');
INSERT INTO Type(Name)
VALUES('HTTP');

INSERT INTO Tag(Name)
VALUES('#NSFW');
INSERT INTO Tag(Name)
VALUES('#Funny');

INSERT INTO Link(Link,Type)
VALUES("http://google.se",2);
INSERT INTO Link(Link,Type)
VALUES("http://Spotify.com/trackid=123",1);

INSERT INTO LinkTag(Link_ID,Tag_ID)
VALUES(1,1);
INSERT INTO LinkTag(Link_ID,Tag_ID)
VALUES(1,2);

SELECT * FROM Link;
SELECT * FROM LinkTag;
SELECT * FROM Tag WHERE Name = "#manga";
SELECT * FROM User;
SELECT * FROM UserNickHistory;
SELECT * FROM Room;
SELECT * FROM RoomTopicHistory;
SELECT * FROM Log;
SELECT * FROM Trademark;
SELECT * FROM Command;

#Hämta basic info om länk
SELECT
Link_ID,
Link
FROM Link
WHERE Link_ID = 5;
#WHERE Link = "http://lol.se/lol";

#Hämta ut alla taggar som e kopplade till en viss länk
SELECT
Link.Link,
Tag.name
FROM Link
JOIN LinkTag ON Link.Link_ID=LinkTag.Link_ID
JOIN Tag ON LinkTag.Tag_ID=Tag.Tag_ID
WHERE Link.Link = "http://mangafox.me/manga/yanki_kun_to_megane_chan/";

#Hämta ut alla länkar som har en viss tagg
SELECT Link.Link, Link.Link_ID
FROM Link
JOIN LinkTag on Link.Link_ID=LinkTag.Link_ID
JOIN Tag ON LinkTag.Tag_ID = Tag.Tag_ID
WHERE Tag.Name = "#diu";

#Hämta ut en random länk baserat på en tag
SELECT Link
FROM Link
JOIN LinkTag on Link.Link_ID=LinkTag.Link_ID
JOIN Tag ON LinkTag.Tag_ID=Tag.Tag_ID
WHERE Tag.Name = 'Funny'
ORDER BY RAND()
LIMIT 1;

#Lägg till ny länk
START TRANSACTION;
INSERT INTO Link(Link,Type) VALUES("http://linkylink",2);
SELECT @A:=Link_ID FROM Link ORDER BY Link_ID DESC LIMIT 1;
#För varje tag du vill lägga in kör denna klump
#Den skapar taggen om den inte redan finns och addar den till länken
INSERT IGNORE INTO Tag(Name) VALUES('Bajs');
SELECT @B:=Tag_ID FROM Tag WHERE Name = 'Bajs';
INSERT INTO LinkTag(Link_ID,Tag_ID) VALUES(@A,@B);
#Slut på Tag-klump
COMMIT;

#Ta bort länk
DELETE FROM Link
WHERE Link_ID = 1;

#Ta bort via namn
SELECT @A:=Link_ID FROM Link WHERE Link = "http://questionablecontent.net/view.php?comic=2456";
DELETE FROM Link
WHERE Link_ID = @A;

#Skapa User
INSERT INTO User(Nick, Password) VALUES('Nickname','MD5at Password');

#Logga in User
SELECT User_ID FROM User WHERE Nick = 'Nickname' AND Password = 'MD5at Password';

#Ta bort User
DELETE FROM User WHERE User_ID = x;

#Adda UserNick till NickHistory
INSERT IGNORE INTO UserNickHistory(Nick, User) VALUES ("New Nickname",x);

#Skapa nytt rum
INSERT INTO Room(ServerName,RoomName,Hash) VALUES('irc.oftc.net','Roomname','md5hash');

#Adda till Room-Topic-History
SELECT @A:=Room_ID FROM Room WHERE RoomName = 'Roomname' AND ServerName = 'irc.oftc.net';
INSERT INTO RoomTopicHistory(Topic, Room_ID) VALUES('NewTopic',@A);

#Adda Reminder
SELECT @A:=User_ID FROM User WHERE Nick = 'UserName';
INSERT INTO Reminder(Reminder, User, Time) VALUES('Glöm inte att...',@A,TIME(NOW()));

#Hämta reminders
SELECT Reminder, User, Time FROM Reminder;

#Logga nånting
SELECT @A:=User_ID FROM User WHERE Nick = 'UserName';
INSERT INTO Log(Command,User) VALUES('!bored',@A);

#Adda en Email
INSERT INTO Email(Email) VALUES('Email@mail.com');

#Hämta random Email
SELECT Email FROM Email ORDER BY RAND() LIMIT 1;

#Adda Telefonnummer
INSERT INTO TelePhonenumber(Number) VALUES('08-124214');

#Adda feed
INSERT INTO RSSFeed(Feed) VALUES('rss://google.se/rss');

#Koppla Feed till User AKA Listan med vilka users som vill ha saker från feeden
SELECT @A:=Feed_ID FROM RSSFeed WHERE Feed = 'rss://google.se/rss';
INSERT INTO UserFeed(User_ID, Feed_ID) VALUES(1, @A);

#Hämta mest använda tagsen
SELECT
Tag.Name
, COUNT(Tag.Name) as tag_count
FROM LinkTag
JOIN Tag ON LinkTag.Tag_ID=Tag.Tag_ID
GROUP BY Tag.Name
ORDER BY tag_count DESC
LIMIT 5;

#Hämta antal länkar utan tag
SELECT
COUNT(l.Link_ID)
FROM Link l
LEFT OUTER JOIN LinkTag lt on l.Link_ID = lt.Link_ID
WHERE lt.Link_ID is null;

#Ta bort senaste insatta länken
DELETE
Link
FROM Link
WHERE Link_ID = lasttag;

#ORDER BY Link_ID DESC
#LIMIT 1;
START TRANSACTION;
SELECT
@A:=Link_ID
FROM Link
ORDER BY Link_ID DESC
LIMIT 1;

DELETE
FROM Link
WHERE Link_ID = @A;

SELECT * FROM Link;
ROLLBACK;

#logga
INSERT INTO Log(command, User) VALUES("!bored #lol", "TBthegr81");

#Skapa user
INSERT INTO User(Nick, Password) VALUES('Nickname','9cdfb439c7876e703e307864c9167a15');

#Adda Trademark
INSERT INTO Trademark(Trademark, TM_Hash, User) VALUES("Cuse Laptop","1bd4d8eb8117fad4e5466fe19a71ef2f","Libre___");

#Räkna länkar som använder en tag.
SELECT
COUNT(LinkTag.Link_ID)
FROM LinkTag
JOIN Tag ON LinkTag.Tag_ID=Tag.Tag_ID
WHERE Tag.Name = "#Webcomic";

#Rensar alla taggar från en länk
SELECT
@A:=Link_ID
FROM Link
WHERE Link = "http://google.se";

DELETE
FROM LinkTag
WHERE Link_ID = @A;

#Get random link with no tag
SELECT
Link
FROM Link l
LEFT OUTER JOIN LinkTag lt on l.Link_ID = lt.Link_ID
WHERE lt.Link_ID is null
ORDER BY RAND()
LIMIT 1;

#Adda tag till länk baserat på länk
SELECT
@A:=Link_ID
FROM Link
WHERE Link = "http://google.se";

INSERT
IGNORE INTO Tag(Name)
VALUES('Bajs');

SELECT
@B:=Tag_ID
FROM Tag
WHERE Name = 'Bajs';

INSERT
INTO LinkTag(Link_ID,Tag_ID)
VALUES(@A,@B);

#insert into Commands
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("Random","Random [#tag]","Gets and posts a random link from the database of previously posted and tagged links. If no tag is givven a totaly random link is taken. VARNING, May contain NSFW links.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("Spotify","Spotify [#tag]","Gets and posts a random song from the database of previously posted and tagged song. If no tag is givven a totaly song link is taken.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("Dice","Dice","Throws a dice and prints out the resulting number and action");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("Coin","Coin","Flips a coin and returns if it was heads or tails.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("RmLink","Rmlink [Link]","Removes a link from the database. If no Link is givven as argument the last link inserted gets removed. VARNING: Wont work with links that have tags asociated with it... (Going to be fixed)");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("TagCount","TagCount [#tag]","Counts the ammount of links that have the supplied tag. If no tag is givven the top 5 tags and the total ammount of links with and without tags.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("Proxxi","Proxxi","Returns if Proxxi is opened or closed.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("Disable","Disable [Command]","Disables a command so the bot wont listen for it anymore. VARNING: Cant be un-disabled unless the bot is restarted. On the Todo-List...");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("LinkInfo","LinkInfo [Link]","Returns info about a link like its ID and asociated tags.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("Login","Login [username]  [password]","Logs a user in. VARNING: Does not work yet.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("Logout","Logout","Logs a user out. VARNING: Does not work yet.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("Help","Help [Command]","Prints out detailed info and instructions about commands. If no command is given it prints out a general helptext listing all commands.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("YouTube","YouTube [tag]","Returns a random Youtube Video from the database with the supplied tag. If no tag is given a totaly random video is returned.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("CleanTags","CleanTags [link]","Cleans all tags from a link so you can then remove it and give it new tags. VARNING: Uggly Solution that needs to be replaced.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("RmTag","RmTag [link] [#tag]","Removes a specified tag from a link.");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("AddTag","AddTag [link] [#tag]","Adds a tag to a specefied link.");

INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("Dylan","Dylan", "Play the What is Dylan Grillin' game!");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("rollChar","rollChar", "Roll your DnD character");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("cat","cat", "Caaaats!");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("lmgtfy","lmgtfy [String]", "Pastes a link to a Let-Me-Google-That-For-You search");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("pokemon", "pokemon [pokemon name]", "Pastes link to both Serebii.net and Bulbapedia for the Pokemon you want info about");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("GeneralsQuotes", "GeneralsQuotes", "You will get a Quote from one of the Units, now you have to answer what unit that is! For a complete list of units from Generals type !Generals Units");
INSERT INTO Command(Command, Short_Description, Long_Description) VALUES("GeneralsUnits", "GeneralsUnits", "Lists all Units in Generals for the Generals Quote-quessing Game!");


#Get a command
SELECT * FROM Command;

#Get a Pokemon
SELECT Dex_ID FROM Pokemon WHERE Name = "Pikachu";

#Insert Unit
INSERT INTO Unit(Unit_Name, Faction) VALUES("Bomb Truck", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Car Bomb", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Hijacker", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Jarmen Kell", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Maurader Tank", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Quad Cannon", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Rebel", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Rocket Buggy", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("RPG Trooper", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Scorpion Tank", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("SCUD Launcher", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Technical", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Terrorist", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Toxin Tractor", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Worker", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Radar Van", "GLA");
INSERT INTO Unit(Unit_Name, Faction) VALUES("","");

INSERT INTO Quotes(Quote, Unit_ID) VALUES("GLA Postal Service", 1);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I have a delivery", 1);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Sorry, no tracking numbers", 1);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I will send a clear message", 1);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Loaded and ready", 1);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I'm not responsible for damaged goods", 1);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Bomb truck ready for dispatch", 1);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Ready for impact", 2);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Driver at the wheel", 2);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("My car is wired up", 2);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I own this road", 2);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Nothing will stop me", 2);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I will complete my task", 2);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Sight unseen", 3);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("How about a test drive eh?", 3);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Need a new vehicle?", 3);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I can drive anything", 3);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("They won't expect anything", 3);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let's go car shopping", 3);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("I hear you", 4);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I am waiting", 4);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("What's the job?", 4);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Preparing my tools", 4);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("No one escapes", 4);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Who's the target?", 4);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Ssshhh! I can't be seen", 4);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Tread's tightened", 5);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("What can I say, I'm a taker", 5);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("You got something for me?", 5);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("It all works", 5);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Armor bolted in", 5);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Maurader Tank assembled", 5);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Quad Cannon", 6);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Four on the floor", 6);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Huh?", 6);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("What?", 6);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I'll punch up the next thing that moves", 6);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Don't get on my bad side", 6);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("It's mine", 6);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I'l take it", 6);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Give me that!", 6);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let's fight", 7);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We will not be oppressed", 7);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Our courage will be seen by all", 7);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("No cost is too great", 7);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("The higher order shall reign", 7);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Our way is true", 7);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Prepared to fight", 7);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Ready for war", 7);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Yes?", 8);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I ride alone", 8);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I will make my own path", 8);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Roads? How boring", 8);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("What is it?", 8);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Rocket barrage ready", 8);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let's kick up some dirt", 8);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Leave them in the dust!", 8);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("They will be humiliated!", 8);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Shh, I sense danger may be near", 9);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Quick, ready yourselves", 9);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("The tunnels will protect us", 9);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Defending the tunnel network", 9);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Our land must be preserved", 9);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Be on watch for the enemy", 9);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Guarding the GLA underground", 9);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Scorpion Tank", 10);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("No lack of courage", 10);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I will do my best", 10);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Our will is strong", 10);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Light tank of the GLA", 10);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Scorpion, ready to sting", 10);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("SCUD launcher poised to strike", 11);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Look at my beautiful weapon", 11);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We'll send them running", 11);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Thier tragedy will come", 11);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Something for the masses", 11);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Hehehehehehehe", 11);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Shall I push the button?", 11);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("What do you want?", 12);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("What?", 12);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We fear nothing", 12);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Our clan is strong", 12);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We take what we need", 12);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let's get in the fight", 12);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("I am prepared", 13);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("They will fear us", 13);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We must have justice", 13);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Our following is strong", 13);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I love a crowd", 13);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I will die for our cause", 13);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I'll make the sacrifice", 13);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("My tank is full", 14);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Hehe, aged to perfection", 14);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Potency guaranteed", 14);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We have generous portions", 14);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("My own special brew", 14);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Would you like to try some?", 14);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Fresh out of the lab", 14);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Yes?", 15);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("What do you want with me?", 15);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Do not hurt me", 15);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I will obey", 15);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I'm just a peasant", 15);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I'm hungry", 15);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Cannot we live in peace?", 15);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I will work", 15);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Ow, ok ok I will work", 15);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Thank you for the new shooes!", 15);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("my work is complete.", 15);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("i wil do what i must.", 15);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Lurking eyes at your disposal", 16);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Nothing is private", 16);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let us see what they are up to", 16);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Keep me out of sight", 16);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Expanding service area", 16);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("GLA radar services", 16);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let us go where it is safe", 16);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("What station you need,general?", 16);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We get signal everywhere.", 16);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Better reception there?", 16);

INSERT INTO Unit(Unit_Name, Faction) VALUES("Battlemaster","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Black Lotus","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Bulldozer","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Dragon Tank","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Gattling Tank","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Hacker","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Inferno Cannon","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Mig","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Nuke Cannon","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Overlord","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Red Guard","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Supply Truck","China");
INSERT INTO Unit(Unit_Name, Faction) VALUES("Troop Crawler","China");

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Battlemaster tank reporting", 17);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Where is the battle?", 17);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Fighting for the red army", 17);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Serving China's needs", 17);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Right here", 17);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("China's tank division", 17);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("China needs us there", 17);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Crush the enemy", 17);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("I'll get through", 18);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Need a transfer", 18);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("What is it", 18);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I'm listening", 18);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Give me a job", 18);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Alright I'm here, let's have an update", 18);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I have work to do here", 18);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("China has been generous", 19);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I build for China", 19);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We have big plans", 19);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We will live in prosperity", 19);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let's build", 19);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Building the chinese empire", 19);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Storage tanks sealed", 20);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Pressure levels set", 20);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Temperatures stable", 20);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Everything ready here", 20);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Adjusting gas valves", 20);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("It is very hot in here", 20);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Gattling Tank ready for action", 21);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Gun barrels spinning", 21);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let's get into battle", 21);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Need a bullet barrage?", 21);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Keep the cylinders oiled", 21);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I have many bullets to spare", 21);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Laptop in hand", 22);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I can cripple thier facilities", 22);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Batteries charged", 22);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Uplink cables ready", 22);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("There's always a way in", 22);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("No system is safe", 22);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Nobody will notice that their money is missing.", 22);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Inferno cannons loaded", 23);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Hehehehehe", 23);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let's rain some fire", 23);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Everything will go up in smoke", 23);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("When do I get started?", 23);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let's start an inferno", 23);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Aircraft standing by", 24);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Mig in flight", 24);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("China's airforce", 24);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We defend China's airspace", 24);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Strapped in and ready", 24);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Tactical fighter reporting", 24);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("We bear gifts", 25);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Nuke warheads preserved", 25);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("They will never forget", 25);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We will be generous", 25);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("The mother of all weapons", 25);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Behold the bringer of light", 25);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("The final word", 25);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("This is the Overlord Tank", 26);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("It is time", 26);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("I am big", 26);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Are you ready?", 26);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("What do you need?", 26);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Overlord is waiting", 26);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Extra large", 26);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Yes?", 27);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Defenders of peace", 27);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("The people's army", 27);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We are the red guard", 27);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Standing attention", 27);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Ready for orders", 27);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We stand together", 27);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("China, do not forget me! ", 27);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Supply truck here", 28);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("There is much money to be made", 28);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("This is tight schedule", 28);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We are pretty busy here", 28);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Where do you want these?", 28);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let's pick up some goods", 28);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Resuming the supply rounds", 28);

INSERT INTO Quotes(Quote, Unit_ID) VALUES("Troop Crawler signing in", 29);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("They can't hide from me", 29);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Next stop, the front line", 29);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("China's armored transport", 29);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("We have plenty of seats", 29);
INSERT INTO Quotes(Quote, Unit_ID) VALUES("Let's get these men to the battle", 29);

SELECT * FROM Unit;

#Get a random Unit + Quote from that unit
SELECT
Unit.Unit_Name,
Quotes.Quote
FROM Quotes
JOIN Unit ON Unit.Unit_ID = Quotes.Unit_ID
ORDER BY RAND()
LIMIT 1;

SELECT Unit_Name
FROM Unit;

SELECT * FROM Unit;

#UPDATE Unit SET Faction = "GLA" WHERE Unit_ID > 0;