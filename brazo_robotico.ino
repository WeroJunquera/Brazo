#include <Wire.h>
#include <EEPROM.h>
#include <Servo.h>
Servo servo1;
Servo servo2;
Servo servo3;
Servo servo4;
int input;
int mServo1 = 90;
int mServo2 = 90;
int mServo3 = 90;
int mServo4 = 90;
int eepromEntrada;
int eepromAngulo;
int boton = 7;
int ledServo1 = 4;
int ledServo2 = 5;
int ledServo3 = 6;
int ledServo4 = 7;
int duracion = 250; //Duración del sonido
int fMin = 2000; //Frecuencia más baja que queremos emitir
int fMax = 4000; //Frecuencia más alta que queremos emitir
int i = 0;
int buzzer = 15;
int aut = 0;

void setup() {
  pinMode (boton, INPUT);
  input = 0;
  Serial.begin(9600);
  servo1.attach(10);
  servo2.attach(9);
  servo3.attach(8);
  servo4.attach(11);
  pinMode(ledServo1, OUTPUT);
  pinMode(ledServo2, OUTPUT);
  pinMode(ledServo3, OUTPUT);
  pinMode(ledServo4, OUTPUT);
  pinMode (buzzer, OUTPUT); //pin configurado como salida
}

void loop() {
  if (boton == 1) {

  }

  int estado  = digitalRead(boton);
  eepromEntrada = EEPROM.read(1);
  eepromAngulo = EEPROM.read(2);

  if (eepromAngulo != 0 && eepromAngulo != 180) {


    input = eepromEntrada;
    if (input == '0') {

    } else if (input == '1') {
      aut = 0;
      mServo1 = eepromAngulo;
      arriba(input);
    } else if (input == '2') {
      aut = 0;
      mServo1 = eepromAngulo;
      abajo(input);
    } else if (input == '3') {
      aut = 0;
      mServo4 = eepromAngulo;
      arriba(input);
    } else if (input == '4') {
      aut = 0;
      mServo4 = eepromAngulo;
      abajo(input);
    } else if (input == '5') {
      aut = 0;
      mServo2 = eepromAngulo;
      arriba(input);
    } else if (input == '6') {
      aut = 0;
      mServo2 = eepromAngulo;
      abajo(input);
    } else if (input == '7') {
      aut = 0;
      mServo3 = eepromAngulo;
      arriba(input);
    } else if (input == '8') {
      aut = 0;
      mServo3 = eepromAngulo;
      abajo(input);
    }
  }
  if (Serial.available() > 0) {
    input = Serial.read();
    if (input == '0') {

    } else if (input == '1') {
      aut = 0;
      arriba(input);
    } else if (input == '2') {
      aut = 0;
      abajo(input);
    } else if (input == '3') {
      aut = 0;
      arriba(input);
    } else if (input == '4') {
      aut = 0;
      abajo(input);
    } else if (input == '5') {
      aut = 0;
      arriba(input);
    } else if (input == '6') {
      aut = 0;
      abajo(input);
    } else if (input == '7') {
      aut = 0;
      arriba(input);

    } else if (input == '8') {
      aut = 0;
      abajo(input);
    }
    else if (input == '9') {
      servo1.write(90);
      servo2.write(90);
      servo3.write(90);
      servo4.write(90);
      digitalWrite(ledServo1, HIGH);
      digitalWrite(ledServo2, HIGH);
      digitalWrite(ledServo3, HIGH);
      digitalWrite(ledServo4, HIGH);
      delay(100);
      digitalWrite(ledServo1, LOW);
      digitalWrite(ledServo2, LOW);
      digitalWrite(ledServo3, LOW);
      digitalWrite(ledServo4, LOW);
      aut = 1;
      //automatico();
    }
    delay(2500);

  } else {

  }


}
void arriba(char entrada) {

  if (entrada == '1') {
    if (aut == 0) {
      for (; mServo1 <= 130; mServo1++) {
        servo1.write(mServo1);
        delay(10);
        digitalWrite(ledServo1, HIGH);
        EEPROM.write(1, entrada);
        EEPROM.write(2, mServo1);

        if (boton == 1) {

          quieto1(mServo1);
        }
      }
    } else if (aut == 0) {
      mServo1 += 10;
      if (mServo1 <= 130) {
        servo1.write(mServo1);
        
        digitalWrite(ledServo1, HIGH);
       
        input = 0;
      }

    }
    digitalWrite(ledServo1, LOW);
  }

  if (entrada == '3') {
    for (; mServo4 >= 30; mServo4--) {
      servo4.write(mServo4);
      delay(10);
      digitalWrite(ledServo4, HIGH);
      EEPROM.write(1, entrada);
      EEPROM.write(2, mServo4);
      if (boton == 1) {
        quieto4(mServo4);
      }
    }
    digitalWrite(ledServo4, LOW);
  }
  if (entrada == '5') {
       if (aut == 0) {
      for (; mServo2 <= 180; mServo2++) {
        servo2.write(mServo2);
        delay(10);
        digitalWrite(ledServo2, HIGH);
        EEPROM.write(1, entrada);
        EEPROM.write(2, mServo2);

        if (boton == 1) {

          quieto2(mServo2);
        }
      }
    } else if (aut == 0) {
      mServo2 += 10;
      if (mServo2 <= 180) { 
        servo2.write(mServo2);        
        digitalWrite(ledServo2, HIGH);
        input = 0;
      }

    }
    digitalWrite(ledServo2, LOW);
  }
  if (entrada == '7') {
    for (; mServo3 >= 90; mServo3--) {
      servo3.write(mServo3);
      delay(10);
      digitalWrite(ledServo3, HIGH);
      EEPROM.write(1, entrada);
      EEPROM.write(2, mServo3);
      if (boton == 1) {

        quieto3(mServo3);
      }
    }
  }
  digitalWrite(ledServo3, LOW);
}

void abajo(char entrada) {
  if (entrada == '2') {
      for (; mServo1 >= 60; mServo1--) {
      servo1.write(mServo1);
      delay(10);
      digitalWrite(ledServo1, HIGH);
      EEPROM.write(1, entrada);
      EEPROM.write(2, mServo1);
      if (boton == 1) {

        quieto1(mServo1);
      }
    }
    digitalWrite(ledServo1, LOW);
  }
  if (entrada == '4') {
    for (; mServo4 <= 140; mServo4++) {
      servo4.write(mServo4);
      delay(10);
      digitalWrite(ledServo4, HIGH);
      EEPROM.write(1, entrada);
      EEPROM.write(2, mServo4);
      if (boton == 1) {

        quieto4(mServo4);
      }
    }
    digitalWrite(ledServo4, LOW);
  }
  if (entrada == '6') {
    for (; mServo2 >= 70; mServo2--) {
      servo2.write(mServo2);
      delay(10);
      digitalWrite(ledServo2, HIGH);
      EEPROM.write(1, entrada);
      EEPROM.write(2, mServo2);
      if (boton == 1) {

        quieto2(mServo2);
      }
    }
    digitalWrite(ledServo2, LOW);
  }
  if (entrada == '8') {
    for (; mServo3 <= 180; mServo3++) {
      servo3.write(mServo3);
      delay(10);
      digitalWrite(ledServo3, HIGH);
      EEPROM.write(1, entrada);
      EEPROM.write(2, mServo3);
      if (boton == 1) {

        quieto3(mServo3);

      }
    }
  }
  digitalWrite(ledServo3, LOW);
}

void quieto1(int angulo) {

  servo1.write(angulo);
}
void quieto2(int angulo) {

  servo2.write(angulo);
}
void quieto3(int angulo) {

  servo3.write(angulo);
}
void quieto4(int angulo) {
  servo4.write(angulo);
}



void automatico() {
  abajo('4');//izquierda
  delay(2000);
  abajo('2');//abajo hombro
  delay(2000);
  abajo('6');//abajo codo
  delay(2000);
  arriba('7');//abrir pinza
  delay(2000);
  abajo('8');//cierra pinza
  delay(2000);
  arriba('1');//arriba hombro
  delay(2000);
  arriba('5');//arriba codo
  delay(2000);
  arriba('3');//derecha
  delay(2000);
  abajo('2');//abajo hombro
  delay(2000);
  abajo('6');//abajo codo
  delay(2000);
  arriba('7');//abrir pinza
  delay(2000);
  abajo('8');//cierra pinza
  delay(2000);
  arriba('1');//arriba hombro
  delay(2000);
  arriba('5');//arriba codo
input = 9;
}
