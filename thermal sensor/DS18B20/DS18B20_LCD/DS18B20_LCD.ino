#include <OneWire.h>
#include <DallasTemperature.h>
#include <LiquidCrystal.h>

LiquidCrystal lcd(7, 6, 5, 4, 3, 2);

#define ONE_WIRE_BUS 12

OneWire oneWire(ONE_WIRE_BUS);

DallasTemperature sensors(&oneWire);

float Celsius = 0;
float Fahrenheit = 0;

void setup() {
  sensors.begin();
  lcd.begin(20, 4);
  lcd.print("Room Temperature");
}

void loop() {
  sensors.requestTemperatures();

  Celsius = sensors.getTempCByIndex(0);
  Fahrenheit = sensors.toFahrenheit(Celsius);

  lcd.setCursor(0, 1);
  lcd.print(Celsius);
  lcd.print(" Celsius  ");
  lcd.setCursor(0, 2);
  lcd.print(Fahrenheit);
  lcd.println(" Fahrenheit ");

  delay(100);

}
