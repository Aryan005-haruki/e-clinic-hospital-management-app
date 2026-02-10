# Hospital App - Demo Mode Instructions

## ✅ APP NOW WORKS WITHOUT FIREBASE!

### 🎯 DOCTOR LOGIN (Works Immediately!)
**No Firebase needed - Uses local SQLite database**

**Available Demo Doctors:**
1. **Dr. Ajay Kumar** - Password: `ajay12345`
2. **Dr. Saurav Kalsoor** - Password: `saurav123`  
3. **Dr. Abhinav Thakur** - Password: `abhinav12`
4. **Dr. Rohit Sharma** - Password: `rohit1234`
5. **Dr. Priya Singh** - Password: `priya12345`

**How to Login:**
1. Open app → Select "Doctor"
2. Choose any doctor from dropdown
3. Enter password (see above)
4. Click Login → Done! ✅

---

### 🎯 PATIENT LOGIN (Demo Mode!)
**Works without Firebase using test credentials**

**Demo Phone Number:** `1234567890`
**Demo OTP:** `123456`

**How to Login:**
1. Open app → Select "Patient"
2. Enter phone: `1234567890`
3. Click "Generate OTP"
4. You'll see message: "DEMO MODE: Use OTP: 123456"
5. Enter OTP: `123456`
6. Click "Verify" → Done! ✅

---

## 🚀 WHAT WAS CHANGED:

### Doctor Login:
- ✅ Removed Firebase Firestore dependency
- ✅ Using local SQLite database (DoctorDbHelper)
- ✅ Pre-populated with 5 sample doctors
- ✅ Works completely offline

### Patient Login:
- ✅ Added demo mode
- ✅ Special phone number (1234567890) bypasses Firebase
- ✅ Fixed OTP (123456) for testing
- ✅ Real Firebase still works if configured

---

## 📱 TESTING:

### Test Doctor Features:
1. Login as "Dr. Ajay Kumar" (password: ajay12345)
2. You can:
   - View dashboard
   - Manage appointments
   - Create prescriptions
   - View patient list

### Test Patient Features:
1. Login with demo credentials (phone: 1234567890, OTP: 123456)
2. You can:
   - Browse doctors
   - Book appointments (demo mode - won't save to cloud)
   - View profile
   - Upload records (local only)

---

## ⚠️ LIMITATIONS (Demo Mode):

1. **Patient data** won't sync to cloud (no Firebase)
2. **Appointments** are local only
3. **Medical records** won't upload to cloud storage
4. **OTP** is fixed - no real SMS

---

## 🔧 TO USE WITH REAL FIREBASE (Optional):

If you want full functionality:
1. Create FREE Firebase project: https://console.firebase.google.com
2. Add Android app with package: `com.example.tatwa10`
3. Download `google-services.json`
4. Replace in `app/` folder
5. Enable Phone Authentication
6. Add SHA-1: `7A:7B:45:54:8F:E4:08:BF:BC:8A:19:A2:C6:DD:83:BE:E3:6:02:62:BD`

Then real phone numbers will work!

---

## ✅ NOW YOU CAN TEST EVERYTHING WITHOUT FIREBASE! 🎉
