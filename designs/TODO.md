# TODOs

## Wie könnten Verträge dargestellt werden?

`List<Buchung> buchungen`

- Beträge können sich im Laufe der Zeit verändern.
    - Es muss eine Liste von Buchungen verarbeitet werden.
- Wie kann ein Vertrag automatisiert erkannt werden?
    - gleicher Tag jeden Monat (Wochenende & Feiertage beachten),
    - gleicher Verwendungszweck,
    - gleicher Auftraggeber,
    - gleicher Zahlungsbeteiligter.

## Bank aktualisieren

- Bezeichnung ändern (nach Import haben Banken nur eine 4-stellige Bezeichnung abgeleitet vom BIC).
- BIC sollte erstmal nicht geändert werden können.

## Bank löschen

- Wie ein DELETE aufrufen? Über jQuery?
- Wenn Bank noch Bankverbindungen hat, sollte Fehler gemeldet werden.

## Bankverbindung aktualisieren

- Sollte erstmal nicht möglich sein.

## Bankverbindung löschen

- Wie ein DELETE aufrufen? Über jQuery?

