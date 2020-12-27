# About PARKHERO
Welcome to PARKHERO! A simple app to help you find and reserve parking anywhere in Macedonia in just a few clicks! Available only for Android.

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

