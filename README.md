# TPO3

It's a project focused around practicing DateTime libraries and also YAML notation.

Functionality:
Program reads from a YAML file (example)
host: localhost
port: 7777
concurMode: true   # czy klienci działają równolegle?
showSendRes: true  # czy pokazywać zwrócone przez serwer wyniki metody send(...) 
clientsMap: # id_klienta -> lista żądań
  Asia: 
    - 2016-03-30T12:00 2020-03-30T:10:15
    - 2019-01-10 2020-03-01
    - 2020-03-27T10:00 2020-03-28T10:00
    - 2016-03-30T12:00 2020-03-30T10:15
  Adam: 
    - 2018-01-01 2020-03-27 
    - 2019-01-01 2020-02-28
    - 2019-01-01 2019-02-29
    - 2020-03-28T10:00 2020-03-29T10:00

And returns the time passed between the dates (example)

Asia
Od 22 stycznia 2017 (niedziela) do 12 kwietnia 2020 (niedziela)
 - mija: 1176 dni, tygodni 168.00
 - kalendarzowo: 3 lata, 2 miesiące, 21 dni
Od 27 marca 2020 (piątek) godz. 10:00 do 28 marca 2020 (sobota) godz. 10:00
 - mija: 1 dzień, tygodni 0.14
 - godzin: 24, minut: 1440
 - kalendarzowo: 1 dzień
Adam
Od 26 grudnia 2004 (niedziela) do 25 listopada 2016 (piątek)
 - mija: 4352 dni, tygodni 621.71
 - kalendarzowo: 11 lat, 10 miesięcy, 30 dni
Od 28 marca 2020 (sobota) godz. 10:00 do 29 marca 2020 (niedziela) godz. 10:00
 - mija: 1 dzień, tygodni 0.14
 - godzin: 23, minut: 1380
 - kalendarzowo: 1 dzień
Sara
Od 31 lipca 2016 (niedziela) do 22 stycznia 2020 (środa)
 - mija: 1270 dni, tygodni 181.43
 - kalendarzowo: 3 lata, 5 miesięcy, 22 dni
Od 31 stycznia 1900 (środa) do 11 stycznia 2020 (sobota)
 - mija: 43809 dni, tygodni 6258.43
 - kalendarzowo: 119 lat, 11 miesięcy, 11 dni
