## COLLEGE ADMISSION FORM APP

---

An android studio app for online college admission for the first years with added admission form fees payment method using google pay.
### Installation

---

Android studio app - https://developer.android.com/studio in your computer
Google Pay app in your mobile
### Instructions 

---
Language: JAVA
Database: SQLite

Java codes are available in
```
AdmissionFormApp/app/src/main/java/com/example/app/
```
Xml codes(which defines the layout) are available in
```
AdmissionFormApp/app/src/main/res/layout/
```
* activity_main.xml - Login Page
* activity_register.xml - Registration Page
* Others are for forms and db

### How to use?

---

* Download and open the folder in android studio app
* In 'gpay.java' file, edit the variable values as follows (Read instructions to find the file)
     > * amount - set your desired admission fees price
     > * merchantName - set your name
     > * merchantUPIID - set your google pay UPI id
* Onclicking the run, the first page for 'logging in' will be shown
* Click on register before you login.
* Fill the form and complete payment 
* Then click on logout

    There is an admin login who can view/delete the submissions. Use the following:
    > Username: admin
    > Password: admin


© [iholy19](https://github.com/iholy19)
