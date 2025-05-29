import 'package:flutter/services.dart';

class ValidationService {
  static const platform = MethodChannel('app.locker/validate');

  static Future<bool> validateWithBackend() async {
    try {
      final result = await platform.invokeMethod<bool>('validate');
      return result ?? false;
    } catch (e) {
      print("Validation failed: $e");
      return false;
    }
  }
}
