# About PARKHERO
Welcome to PARKHERO! A simple app to help you find and reserve parking anywhere in Macedonia in just a few clicks! Available only for Android.


# Installation Guide
To install this application simply download the APK file to your phone. After downloading the application on your phone install it by going to your File Manager and clicking on the file name. 

# Usage
Once you've installed the app to your it's time to launch it. If you already have an account, go ahead and log in, but if you're using this app for the first time, you need to register. After successfully loging in, select the city where you wish to reserve a parking spot. After selecting the city, choose a date and time. Once you choose your date and time, you will be given a list of parking in the city you've selected. If the reserve button is disabled, that might be due to the fact there are no more available spots in the parking lot you wish to reserve. But if all the reserve buttons are disabled, that means that you've reached the maximum number of reservations. Once the reservation is complete, you will be given a QR code, which you can scan to see the location of the selected parking lot, or you can simply click on the button to navigate to the parking lot.
Thank you for using PARKHERO! :)

# Contents
1. Main Activity - or LogIn Activity
2. Register Acticity
3. Cities Activity
4. Reservation Form 
5. Parking Places
6. Reservation Confirmation
7. My Reservations

## Main Activity
In this activity, the user logs into his account on our aplication (if they're registered) by providing the username and password. If the user is not registered in the system, by clicking on the assigned button, they can transfer to the Register activity.

## Register Activity
Here, the user can provide their name, surname, email, age, select their gender and choose their username and password and create an account and register to the application.

## Cities Activity
After the user has loged in to their account, they can choose the city they want to reserve a parking spot. The cities that work with this app are provided in a recycler view.

## Reservation Form
After choosing their desired city, the user needs to select the date they wish to reserve a parking spot, and the time slot.

## Parking Places
Once the user has selected their desired city, date and time slot, a list of parkings in the desired city are shown in a recycler view, with a red square showing the number of reserved spots for that date and time, and a green square showing the number of free spots. If the number of free parkin spots is 0 in a parking lot, the reserve button in that parking lot will be disabled. Also, if the user alredy has made 3 reservations, all of the reserve buttons will be disabled.

## Reservation Confirmation
Once the reservation is completed, the user gets the reservation confirmation page, where he can scan the QR code to get the coordinates od the parking he chose, or he can click on the button to get a navigation to the parking location.

## My Reservations
This can be accesed from the toolbar from the Cities Activity, Reservation Form, Parking Places and Reservation confirmation. It shows all the reservations the current user has made, the name of the parking lot, the date and the time, and also the QR code to get the location of the parking lot.




