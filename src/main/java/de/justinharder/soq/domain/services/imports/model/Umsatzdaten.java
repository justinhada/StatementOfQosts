package de.justinharder.soq.domain.services.imports.model;

public record Umsatzdaten(String inhaberkonto, String buchungsdatum, String valuta, String auftraggeber, String iban,
						  String bic, String verwendungszweck, String betrag, String waehrung, String kundenreferenzen,
						  String bankreferenz, String primatnota, String transaktionsCode, String transaktionsText) {}
