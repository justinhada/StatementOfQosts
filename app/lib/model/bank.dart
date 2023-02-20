class Bank {
  final String id;
  final String bezeichnung;
  final String bic;

  const Bank({required this.id, required this.bezeichnung, required this.bic});

  factory Bank.fromJson(Map<String, dynamic> json) {
    return Bank(
      id: json['id'],
      bezeichnung: json['bezeichnung'],
      bic: json['bic'],
    );
  }

  @override
  String toString() {
    return "Bank{ID=$id, Bezeichnung=$bezeichnung, BIC=$bic}";
  }
}
