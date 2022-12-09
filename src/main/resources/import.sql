INSERT INTO Bank(ID, Bezeichnung, BIC)
VALUES ("46c317ae-25dd-4805-98ca-273e45d32815", "Oldenburgische Landesbank AG", "OLBODEH2XXX"),
       ("aaa8a25c-7589-4434-b668-4a78ab000628", "VR BANK Dinklage-Steinfeld eG", "GENODEF1DIK");
INSERT INTO Benutzer(ID, Nachname, Vorname, Firma, Art)
VALUES ("1eaa1624-69f3-4634-a96f-a3a9fd9c7bb4", "Harder", "Justin", "", "PRIVATPERSON"),
       ("c09e5a5d-96c7-4607-b053-e8091a9481a7", "Tiemerding", "Laura", "", "PRIVATPERSON");
INSERT INTO Login(ID, EMailAdresse, Benutzername, Salt, Passwort, BenutzerID)
VALUES ("759369f9-a353-4912-a145-7ac5414afd24", "justinharder@t-online.de", "hard3r", "+aHTB085LIGJ4/YUBNXGmg==",
        "Dg3J85AZ4eh8YnA8hd9A8Q==", "1eaa1624-69f3-4634-a96f-a3a9fd9c7bb4"),
       ("d883cd10-6bb8-4e7e-ad6d-65c1090a9bbe", "laura.tiemerding@icloud.com", "tiey", "f0BhmnZ1p0QG5ghsJpTbTw==",
        "jSnZLFA1mnCj6RFMNIiakw==", "c09e5a5d-96c7-4607-b053-e8091a9481a7");
INSERT INTO Bankverbindung(ID, IBAN, BankID)
VALUES ("37854473-fa07-4570-a094-3794cd555aa4", "DE87280200504008357800", "46c317ae-25dd-4805-98ca-273e45d32815"),
       ("87ada42c-a758-4595-a158-f69ba73fd77a", "DE28280651080012888000", "aaa8a25c-7589-4434-b668-4a78ab000628");
