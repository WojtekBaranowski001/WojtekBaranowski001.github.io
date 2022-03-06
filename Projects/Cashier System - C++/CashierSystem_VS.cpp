// I was asked to create a program which will be used by the supermarket.
// User will type in the barcode, program will look for the barcode in the arrays, calculate the total price and ask user for the money.
// Program writen by Wojtek Baranowski.
// Last update: 17/12/2019

#include <iostream>
#include <string>
#include <iomanip>
using namespace std;

void helloMessage();
void barcode_input();
void sum();
void customer();
string items[10] = { "Milk", "Bread", "Chocolate", "Towel","Toothpaste", // This three lines are arrays. They contain infromation about the item name, barcode and price.
							 "Soap","Pen","Biscuits","Lamp","Battery" }; // Evrytime when user types in the barcode, program will search the array for the right barcode.
string barcode[10] = { "0120001", "0120002", "0120003", "0120004", "0120005",
					   "0120006", "0120007", "0120008", "0120009", "0120010" };
float price[10] = { 10.50,5.50,8.00,12.10,6.75,
					5.20,2.00,4.45,20.50,10.00 };
// Variables used in the program. 
int i;
string bar;
float total_price;
float user_money = 0;
float change;
char new_customer;
string finish;
int basket;

int main()
{
	{
		// Functions. 
		helloMessage();

		barcode_input();

		sum();

		customer();

	}
}

void helloMessage() // Function for hello message.
{
	cout << "*********************************************** " << endl; // This is little "hello" from the shop to the user.
	cout << "*       WELOCME TO HERTS SUPERMARKET          * " << endl; // Evrytime when user will run the program this will come up.
	cout << "*    Scan the barcode or type it manually     * " << endl;
	cout << "***********************************************\n" << endl;
}

void  barcode_input() // Function for all items, barcodes and prices.
{
	cout << setw(12) << "  |  ITEM  |  " << setw(12) << "  |  BARCODE  |  " << setw(12) << "  |  PRICE  |  " << endl; // This line is about displaying the barcode, item name and it price.
	for (i = 0; i < 10; i++)
	{
		cout << setw(10) << items[i] << setw(16) << barcode[i] << setw(11) << char(156) << price[i] << setprecision(2) << fixed << endl; // This line is about showing the user barcode, item and price. It's a little help for the users, so they know what barcode to use.
	}
	cout << "--------------------------------------------- " << endl;
	cout << "\n***********************************************" << endl;
	cout << "* Please use table above to find the barcode. * " << endl;
	cout << "***********************************************" << endl;
	

	do // This is the loop which will repete the statement for barcode. 
	{
		bool error = true;

		cout << "\nPlease enter a barcode (Press f if you would to finish shopping) : "; // This statement will be repeted everytime till the input is f.
		cin >> bar;


		for (int i = 0; i < 10; i++)
		{

			if (barcode[i] == bar) // If barcode is correct and program find's it in the array, it will display the program name and it's price and ask user if shopping is finished. 
			{
				error = false;
				cout << "\nItem bought is " << items[i] << " which costs " << char(156) << price[i] << endl;
				
				total_price = total_price + price[i]; // This is the calculation used for adding up prices of the items.


			}

		}
		if (error == true && bar != barcode[i] && bar != "f") // Here are is an error if statement. So, if the user type in the wrong barcode or not f the error message will pop up and ask user to try again. 
		{
			cout << "[!] Invalid input, please try again. " << endl;
		}


	} while (bar != "f"); // Loop the statement until the input is "f".




}







void sum() // Function for calculating the total price and change.
{

	if (bar == "f")
	{
		cout << "\nTotal price: " << char(156) << total_price << setprecision(2) << fixed << endl; // This message will dispaly total price for all the products that user bought.
	}

	// Below is another do - while loop which will show the user total price, ask for the money and give change if needed.
	do
	{
		cout << "\nAmount to pay: " << char(156) << total_price << setprecision(2) << fixed << endl; // This line will display the total price and user will have to type in the amout of money. 
		cout << char(156);
		cin >> user_money;



		total_price = total_price - user_money;
		if (total_price > 0)
		{
			cout << "\n[!]More money needed: " << char(156) << total_price << setprecision(2) << fixed << endl; // In here if total price is £20 and user typed in £15 the program will tell the user that more money
																												// is needed and it will display how much more money is needed.
		}
		else if (total_price < 0)
		{
			change = -total_price;
			cout << "\nChange given: " << char(156) << change << setprecision(2) << fixed << endl; // This line will display the message about the change. If the total price is £20 and user types in £30
																								  // the programm will show the user how much change he will receive.
		}
		else if (total_price == 0)
		{
			change = total_price; // This line is also about the the change, however if the total price is £10 and user types in £10 it the program will not show any change as the price for the items was  fully paid.
			cout << "\nChange given " << char(156) << change << setprecision(2) << fixed << endl; 

		}

	} while (total_price > 0);

}


void customer() // Function for asking if there is a new customer.
{


	do
	{
		cout << "\nNew customer? (Please press y for yes or n for no) " << endl; // After user will type in their barcode, put in the price, program will ask for the new customer.
		cin >> new_customer;


		if (new_customer == 'y') // If user types in 'y' "thank you" message will come up and the program will repeat.
		{
			cout << "\n\n************************************************** " << endl;
			cout << "* Thank you for shopping with us. Enjoy your day.* " << endl;
			cout << "************************************************** " << endl;
			cout << "\n\n-------------------------------------------------\n" << endl;

			//Below are functions which will repeat when user types in 'y'.
			helloMessage();

			barcode_input();

			sum();

			customer();
		}
		else if (new_customer == 'n') // If customer will input 'n' the program will end and it will show the message. 
		{

			cout << "\n\n************************************************** " << endl;
			cout << "* Thank you for shopping with us. Enjoy your day.* " << endl;
			cout << "************************************************** " << endl;

		}
		else if (new_customer != 'y' && new_customer != 'n') // If users input is not y or n, the error message will come up and user will have to type in the character once again until the input is y or n. 
		{
			cout << "[!] Invalid input, please enter y or n. " << endl;
			continue;
		}

	} while (new_customer != 'y' && new_customer != 'n'); // Loop will run until input is not y or n. 


}
