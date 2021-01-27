# ChatApp

It's a Whatsapp like clone which uses firebase for online auth and storage and Android Room (not complete working on it) for creating local database


## Output
<img align="left" src="https://raw.githubusercontent.com/Mohit0233/ChatApp/master/Output/RegisterPhoneActivity.jpg" width="218px">
<div align="center">
<br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
    <p align="center">
This activity takes the phone number and passes it through itent to the 
SmsVerifyActivity. On click Country's Spinner CountryPickerActivity opens,
which cointains country flag, calling code and country, therefore it 
can be easily searched from the search menu
    </p>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>
<img align="right" src="https://raw.githubusercontent.com/Mohit0233/ChatApp/master/Output/SelectNumber.jpg" width="218px">
<div align="center">
<br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
    <p align="center">
    ContactPickerDialong uses telephonyManager.simCountryIso 
    and SubscriptionManager to get the arrayList of Sim's Data
</p>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>
<img align="left" src="https://raw.githubusercontent.com/Mohit0233/ChatApp/master/Output/SmsVerify.jpg" width="218px">
<div align="center">
<br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
    <p align="center">
SmsVerify Activity uses Firebase Auth to send otp verfication 
and uses Google Cloud Platfrom's SafetyNet Verify Apps API 
which is a Android device app verfification api.As soon as the 
sms comes the Google Play services automatically perform 
verification without user action
    </p>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>

<img align="right" src="https://raw.githubusercontent.com/Mohit0233/ChatApp/master/Output/RegisterName.jpg" width="218px">
<div align="center">
<br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
    <p align="center">
RegisterNameActivity registers the name in Firebase Auth 
current user display name and Profile picture in optional 
and uses MediaStore.ACTION_IMAGE_CAPTURE to get image and 
then stores it to Firebase Storage and stores Uri in 
Fireabase Auth
    </p>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>

<img align="left" src="https://raw.githubusercontent.com/Mohit0233/ChatApp/master/Output/HomeActivity.jpg" width="218px">
<div align="center">
<br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
    <p align="center">
Home Activity contains 3 tab first is camera fragment 
uses Jetpack CameraX libraray can take picture but not 
video recording as it is in CameraX beta and the third 
activity is just for show right now.
    </p>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>

<img align="right" src="https://raw.githubusercontent.com/Mohit0233/ChatApp/master/Output/ChatActivity.jpg" width="218px">
<div align="center">
<br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
    <p align="center">
ChatActivity is noraml Chat with whatsapp like ui animates 
the displayPicture on click action bar for Chat or group 
info as same as whatsapp does
    </p>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>

<img align="left" src="https://raw.githubusercontent.com/Mohit0233/ChatApp/master/Output/CameraFragment.jpg" width="218px">
<div align="center">
<br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
    <p align="center">
Camera Fragment show camera using PreviewView and cameraX.
There are options like pinch to zoom out and do opposite 
for zoom in,touch to focus, uses broadcast manager to click image with lower
volume button and support custom landscape mode
</p>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>
