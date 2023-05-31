class Benutzer {
  final String nachname;
  final String vorname;
  final String firma;
  final Art art;

  Benutzer(
      {required this.nachname,
      required this.vorname,
      required this.firma,
      required this.art});


}

enum Art {
  privatperson,
  unternehmen;
}
