import 'package:shared_preferences/shared_preferences.dart';

class SharedPrefs {
  static const _key = 'unlocked_today';

  static Future<bool> isUnlockedToday() async {
    final prefs = await SharedPreferences.getInstance();
    final lastUnlock = prefs.getString(_key);
    final today = DateTime.now().toIso8601String().substring(0, 10);
    return lastUnlock == today;
  }

  static Future<void> setUnlockedToday() async {
    final prefs = await SharedPreferences.getInstance();
    final today = DateTime.now().toIso8601String().substring(0, 10);
    await prefs.setString(_key, today);
  }
}
