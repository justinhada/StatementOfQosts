import 'dart:convert';

import 'package:app/model/bank.dart';
import 'package:app/utils/endpunkte.dart';
import 'package:http/http.dart';

class BankRessource {
  Future<List<Bank>> findeAlle() async {
    var response = await get(Uri.parse(Endpunkte.banken));

    if (response.statusCode != 200) {}

    return [Bank.fromJson(jsonDecode(response.body))];
  }
}
