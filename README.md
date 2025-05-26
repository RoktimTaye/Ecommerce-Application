# Ecommerce-Application
This is my first Ecommerce backend project which will be modified with frontend UI after a while. This project is built 
in full guidance of an expert mentor and fulltime software developer.

One thing to note that is project main Order service main DTOS ka use ho raha hain eventually or DTOS Model ke classes
ke data ko Model ke classes ke getter setter functions se access kar rahe hain, simple .

Model classes ka main purpose hain jabbhi hum DTO ka use karte hain tab Model classess ke getter and setter se hum 
values / data ko access karte hain .

DTOs main mapping nahi hota isliye getter and setter se dta access karke hum without mapping recursions data ko ek jega 
se dusri jaga me transfer kar sakta hain - Matlab Data Transfer Object ka asli matlab. Or Java ka simple explaination
hain ki object hamesha classes ka banta hain or is case main Model classes ka object banta hain DTO ke andar jake or 
data transfer hota hain.

Ek bat ekdam clear hain ki, Service layer me hume DTOs ke andar Model classes ke data ko dalna hain — getter aur setter
un model classes ka use karke. DTOs ka asli , yani Main kam Service layers main karte hain, yaniki Model classes se data
lena , DTOs main dalne - set karna or convert karna DTOs main , or fir finally Controller main bhejdena or fir Controller
isse Frontend ko bhej deta hain.

Controller ke is data koSpring Boot Auto-identidy karke JSON main convert karke Frontend main bhej deta hain , jiske bad
Frontend is data ko render karke user ko shoe kar deta hain on the UI (User Interface).

JPA use karte wakt jab mapping use karte hain tab joincolumn likhna ya na likhna apne choise hain , kyuki JPA
automatically dusre class ka foreign key us class main dal deta hain jaha par joincolumn likha hoga .
@JoinColumn(name = "order_id")
private Orders order;
Iska matlab hain ki Order class ka key foreign key ke form main ayega.
