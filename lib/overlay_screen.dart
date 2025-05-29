import 'package:flutter/material.dart';
import 'services/validation_service.dart';
import 'utils/shared_prefs.dart';

class OverlayScreen extends StatefulWidget {
  const OverlayScreen({super.key});

  @override
  State<OverlayScreen> createState() => _OverlayScreenState();
}

class _OverlayScreenState extends State<OverlayScreen> {
  String _status = "Validating...";
  bool _canProceed = false;

  @override
  void initState() {
    super.initState();
    _checkValidation();
  }

  Future<void> _checkValidation() async {
    bool alreadyUnlocked = await SharedPrefs.isUnlockedToday();

    if (alreadyUnlocked) {
      setState(() {
        _status = "Already validated for today.";
        _canProceed = true;
      });
      return;
    }

    bool isValid = await ValidationService.validateWithBackend();

    if (isValid) {
      await SharedPrefs.setUnlockedToday();
      setState(() {
        _status = "Access Granted";
        _canProceed = true;
      });
    } else {
      setState(() {
        _status = "Remember who u wanted to be. Go and Solve pending problems.";
        _canProceed = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: _canProceed ? Colors.green[50] : Colors.red[50],
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(_status, style: const TextStyle(fontSize: 24)),
            const SizedBox(height: 20),
            if (!_canProceed)
              const Icon(Icons.lock_outline, size: 60, color: Colors.red),
            if (_canProceed)
              ElevatedButton(
                onPressed: () {
                  // Close the overlay
                  Navigator.pop(context);
                },
                child: const Text("Continue"),
              )
          ],
        ),
      ),
    );
  }
}
