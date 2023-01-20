INSERT INTO Bank(ID, Bezeichnung, BIC)
VALUES ("46c317ae-25dd-4805-98ca-273e45d32815", "Oldenburgische Landesbank AG", "OLBODEH2XXX"),
       ("aaa8a25c-7589-4434-b668-4a78ab000628", "VR BANK Dinklage-Steinfeld eG", "GENODEF1DIK");
INSERT INTO Benutzer(ID, Nachname, Vorname, Firma, Art)
VALUES ("1eaa1624-69f3-4634-a96f-a3a9fd9c7bb4", "Harder", "Justin", "", "PRIVATPERSON"),
       ("c09e5a5d-96c7-4607-b053-e8091a9481a7", "Tiemerding", "Laura", "", "PRIVATPERSON"),
       ("cd4fb688-8854-426f-a3e9-d5c380b4e984", "", "", "Rewe-Markt GmbH", "UNTERNEHMEN"),
       ("bb1d3c64-468d-4109-971c-24dcf08993fc", "", "", "ALTE OLDENBURGER Krankenversicherung AG", "UNTERNEHMEN");
INSERT INTO Bankverbindung(ID, IBAN, BankID)
VALUES ("37854473-fa07-4570-a094-3794cd555aa4", "DE87280200504008357800", "46c317ae-25dd-4805-98ca-273e45d32815"),
       ("87ada42c-a758-4595-a158-f69ba73fd77a", "DE28280651080012888000", "aaa8a25c-7589-4434-b668-4a78ab000628");
INSERT INTO Kontoinhaber(ID, BenutzerID, BankverbindungID)
VALUES ("bc01e451-051b-4bfa-979f-2acff87ba5a2", "1eaa1624-69f3-4634-a96f-a3a9fd9c7bb4",
        "37854473-fa07-4570-a094-3794cd555aa4"),
       ("7f94d5e5-3972-4f44-b81b-994a244ba3ad", "c09e5a5d-96c7-4607-b053-e8091a9481a7",
        "87ada42c-a758-4595-a158-f69ba73fd77a");
INSERT INTO Kategorie(ID, Bezeichnung)
VALUES ("c69a09ed-e5ff-4ac6-90dd-d319ff43b9d6", "Lebensmittel"),
       ("8a977e9d-68e5-415e-948d-09b63b1e2907", "Supplements");
INSERT INTO Umsatz(ID, Datum, Betrag, Verwendungszweck, BankverbindungAuftraggeberID,
                   BankverbindungZahlungsbeteiligterID)
VALUES ("188fae37-0294-4db9-b7e6-7f40c7f390f1", "2020-01-01", "1", "Wohnungsmiete",
        "37854473-fa07-4570-a094-3794cd555aa4", "87ada42c-a758-4595-a158-f69ba73fd77a"),
       ("1da15420-5b77-4d06-9fad-e62c0b62bb6f", "2021-01-01", "-1", "Lohn/Gehalt",
        "87ada42c-a758-4595-a158-f69ba73fd77a", "37854473-fa07-4570-a094-3794cd555aa4"),
       ("7328350d-da3f-436f-bd2e-74c9c3259922", "2021-01-01", "10", "Kindergeld",
        "87ada42c-a758-4595-a158-f69ba73fd77a", "37854473-fa07-4570-a094-3794cd555aa4");
INSERT INTO Buchung(ID, UmsatzID, KategorieID)
VALUES ("bbc67b44-9f75-4a8c-9271-6533f3e062e3", "188fae37-0294-4db9-b7e6-7f40c7f390f1",
        "c69a09ed-e5ff-4ac6-90dd-d319ff43b9d6"),
       ("f1b01d7c-8fc2-4367-ae1e-d4042aa4bd9e", "1da15420-5b77-4d06-9fad-e62c0b62bb6f",
        "8a977e9d-68e5-415e-948d-09b63b1e2907");
