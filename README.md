### 🏦 ATM Management System
Developed by: [Tagaibek Kubatov]
EEAIR24 | ID: 240106011

### 📘 Project Description
A command-line Java-based ATM management system with:

- Secure PIN-based user authentication

- Interest-calculating deposit and credit functions

- Smart withdrawals with denomination constraints

- UTC+6 time-stamped operations

- Full file persistence using JSON

- Rich CLI-based user interface
  ---

### 🎯 Objectives

Objective	Status
- Authenticate Users via PIN	✅ Fully Implemented
- Smart Deposit & Withdrawal Logic	✅ Interest & Constraints
- Credit with Interest Calculation	✅ Done
- Data Persistence	✅ Auto JSON Save/Load
- Time Tracking (UTC+6)	✅ Implemented
- Secure CLI Experience	✅ PIN-Protected
  ---
### ⚙️ Features
🔐 Authentication
PIN-based user login system with retry limits.

🔁 Core Operations
💰 Deposit
Calculates 12-month interest

Adds to user balance

💸 Credit
Adds loan to balance

Tracks interest due over time

🏧 Withdraw
Allows only valid denominations

Prevents overdrafts

Real-world cash simulation

🕒 Time Display
Shows current time in UTC+6

📄 JSON Persistence
users_data.json: All user accounts and balances

🧪 Input Validation
Ensures valid PIN

Prevents withdrawal beyond balance

Rejects invalid denominations or formats
  ---

### 🧩 Modular Design

class ATMUser {
    +pin
    +balance
    +deposit()
    +withdraw()
    +credit()
    +calculateInterest()
}

class Main {
    +main()
    +authFlow()
    +menu()
}
📁 Files Used

File Name	Purpose
users_data.json	Stores user account data
### ✅ Run Instructions
Open project in IntelliJ

Run Main.java

Enter PIN to log in

Follow the command-line options

### 🔐 Example Use Cases
📌 Deposit with Interest
text
Копировать
Редактировать
→ Enter Amount: 10000  
→ 1-year interest (10%) added  
→ New Balance: 11000  
📌 Credit (Loan)
text
Копировать
Редактировать
→ Credit: 5000  
→ Interest (15%) = 750  
→ Repayment Due: 5750  
📌 Withdraw with Denominations
→ Withdraw: 1800  
→ Dispensed: 1x1000, 1x500, 3x100  
### 📬 Contact
Feel free to fork, contribute, or suggest improvements!
Email: [tagaibek.kubatov@alatoo.edu.kg]

