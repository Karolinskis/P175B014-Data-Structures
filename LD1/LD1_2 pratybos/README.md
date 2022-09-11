# LD1_2 pratybos

## Užduoties tikslas 
Perrašyti modulio „Objektinis programavimas 2“ trečiojo laboratorinio darbo programą naudojant java programavimo kalbą.
Naudojamas tas pats užduoties variantas, koks buvo spręstas modulio „Objektinis programavimas 2“ metu.

## Reikalavimai programai
1. Naudojamos bendrinės klasės (generic).
2. Visi reikšmių rinkiniai įgyvendinami, naudojant dinaminį atminties valdymą su vienos krypties ryšiu.
3. Duomenų rinkinio klasė turi realizuoti Iterable<E> sąsają.
4. Rezultatai pateikiami lentelėmis konsolėje ir spausdinami tekstiniuose failuose (vartotojo sąsaja nereikalinga).

## Darbo užduotis
**LD_22. Darbai.** Studentai renkasi projektinių darbų temas. Už projektinių darbų temas yra atsakingi dėstytojai. 
Dėstytojas gali būti atsakingas už keletą projektinių darbų temų. Sudarykite dėstytojų sąrašą (dėstytojo pavardė ir vardas).
Sąrašas turi būti surikiuotas pagal dėstytojų pavardes ir vardus abėcėlės tvarka. 
Pašalinkite iš sąrašo dėstytojus, kurių siūlomų temų studentai nepasirinko. 
Suraskite, kuris dėstytojas turi daugiausiai projektinių darbų.

- tekstiniame faile U22a.txt yra informacija apie studentų pasirenkamus projektinius darbus:
projektinio darbo pavadinimas, studento pavardė, vardas, grupė;
- tekstiniame faile U22b.txt yra informacija apie projektinius darbus: projektinio darbo
pavadinimas, atsakingo dėstytojo pavardė ir vardas, projektiniam darbui skirtų valandų
skaičius. 

Spausdinamas sąrašas turi būti surikiuotas pagal dėstytojų pavardes ir vardus abėcėlės tvarka.
Sudarykite nurodyto dėstytojo (įvedama klaviatūra) projektinių darbų sąrašą. 

## Pradiniai duomenys ir rezultatai
### Pradiniai duomenys
* Konsolė
```
Giedrius Ziberkas
```

* U22a.txt
```
Rekursija,Jusas,Vacius,20
Susietasis sąrašas,Burbaitė,Renata,25
Polimorfizmas,Jančiukas,Mindaugas,21
Deklraratyvusis programavimas,Giedrius,Ziberkas,22
Užklausos LINQ,Nabutaitė,Lina,23
Lambda išraiškų taikymas,Jusas,Vacius,22
```
* U22b.txt
```
Rekursija,Pavardenis,Vardenis,IFF/1-1
Susietasis sąrašas,Pavardaitė,Vardaitė,IFF/1-1
Polimorfizmas,Jonas,Jonaitis,IFF/1-2
Deklraratyvusis programavimas,Arnas,Šablinskas,IFF/1-1
Rekursija,Domantas,Vaičiulis,IFF/1-3
Susietasis sąrašas,Donatas,Kušleika,IFF/1-4
Lambda išraiškų taikymas,Gabija,Grinkevičiūtė,IFF/1-5
```

### Rezultatai:
* Konsolė
```
Daugiausiai projektinių darbų turintis dėstytojas: 
Jusas Vacius
```

* ProfRez.txt
```
Pavardė Vardas 
Jusas Vacius
Burbaitė Renata
Jančiukas Mindaugas
Giedrius Ziberkas
```

* ProjRez.txt
```
Projekto pavadinimas 
Deklraratyvusis programavimas
```

