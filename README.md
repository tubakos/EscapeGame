# EscapeGame
A game with GUI

###A feladat
A játékot egy n×n-es táblán játsszák. Kezdetben a játékos a képernyőn felül és középen, a két üldöző pedig az
alsó sarkokban helyezkedik el. A játékos és az üldözők vízszintes, illetve függőleges irányú mozgásra képesek. Az
üldözők mindig a játékos felé haladnak úgy, hogy ha a függőleges távolság a nagyobb, akkor függőlegesen,
ellenkező esetben vízszintesen mozognak. Az üldözőket a program irányítja, és mindig akkor mozognak, amikor
a játékos. A játékost a nyilakkal lehet a megfelelő irányba mozgatni. Az üldözők célja a játékos elfogása, ami
bekövetkezik, ha valamelyikük hozzáér. A pályán elhelyezkedik még véletlenszerűen 3-6 akna. Ha a játékos vagy
az üldözők egy aknához érnek, az akna felrobban és megsemmisíti azt, aki hozzáért. A játékos feladata
megmenekülni, amihez az üldözőket az aknák segítségével meg kell semmisítenie.
Legyen lehetőség új játék kezdésére a táblaméret megadásával (5x5, 10x10, 15x15). Ügyeljünk arra, hogy az
aknák egymáshoz ne érjenek, illetve kezdetben a játékoshoz és üldözőkhöz sem. A program ismerje fel, ha vége
a játéknak, és jelenítse meg, melyik játékos győzött.

###Tervezés
A fenti feladat alapján a következő objektumelvű programszerkezet alakítható ki:
Szükségünk van egy logikai rétegre, amely a játékkal kapcsolatos információkat tárolja és ismeri annak
szabályait, azoknak megfelelően képes a lehetséges műveleteket elvégezni. Továbbá egy megjelenítő-vezérlő
réteget is kell készítenünk, amely a logikai rétegre épül, megjeleníti és vezérli annak objektumait. A logikai
rétegben a játék felülete tekinthető a rendszer fő objektumának. Ehhez kapcsolódik a karaktereket (játékos, két
vadász, 3-6 akna) reprezentáló objektumok, amelyek a játékban részt vesznek. Szintén a játéktábla
objektumhoz tartoznak a játéktábla mezői. Ezek a mező objektumok a játéktábla részét képezik. A mező
objektumokhoz tartozhat egy karakter objektum, és tartozik pontosan egy mezőszín objektum.
A játéktáblának (osztálya az EscapeGameLogic) kell szolgáltatást nyújtania a mezőkön (osztálya a Cell) levő
karakterek (osztálya a GameCharacter) elhelyezéséhez lekérdezéséhez és a játéklépések elvégzéséhez.
A karakterek absztrakt osztálya, a GameCharacter, és annak leszármazottai (Player, Hunter, Mine) nyújtsanak
szolgáltatást a karakterek tulajdonságainak (pozíció, aktív-e még) lekérdezéséhez, és ha a karakter mozdítható,
akkor a lépések szabályosságának meghatározásához, azaz hogy az adott karakter léphet-e egy adott helyre.
Mivel a felhasználó csak a Player osztály objektumát irányítja, ezért elegendő csak ennél az osztálynál
ellenőrizni a lépés szabályosságát (canMove()). A Hunter osztály objektumai a Player aktuális pozíciója szerint
mozogjanak, a move() metódus a játékos koordinátái és a Hunter objektum aktuális helyzete alapján számolja
ki a szükséges lépés irányát, majd hajtsa végre azt.
Az EscapeGameLogic Cell típusú tömbje tárolja el az egyes mezők pozícióját, és hogy milyen típusú karakter áll
rajta, ha van ilyen.
A megjelenítő-vezérlő réteg alapjául egy JFrame-ből származtatott osztály szolgáljon (EscapeGameFrame).
Ezen rajzoljuk ki a játék felületeként szolgáló táblázatot, melyhez egy JPanel típusú változót használunk, melybe
az elemeket a JButton-ből származtatott GameButton osztályból származtatjuk. Ez tartalmazza a gombok
rácson belül elfoglalt pozícióját. Mivel a logikai réteg objektumai főképp az imént említett panelon jelennek
meg, és vezérlésük is ezen végezhető el, ezért ennek a panelnak ismernie kell a logikai réteg főobjektumát, a
játéktáblát.
Új játék kezdéséhez szükség van még egy menüsorra is, amit a JMenuBar osztály segítségével hozhatunk létre.
Az EscapeGameFrame osztályon belül beágyazott osztályokként lesznek megvalósítva az eseménykezelő
objektumok osztályai.

###Megvalósítás
Az EscapeGameLogic konstruktora a megadott játékszabályok szerint beállítja a
mezők tulajdonságait, és elhelyezi a karaktereket a megfelelő helyzeteken. A Mine típusú objektumok
számát a Random osztály segítségével határozza meg, és ugyancsak véletlenszám generálással
hozza létre ezek koordinátáit, ügyelve arra, hogy a kiválasztott cellában ne álljon más karakter. Ehhez
a cella getGameCharacter(null) metódusának True értéket kell visszaadnia.
A játékos lépéseit ellenőrizni kell, hogy azok koordinátái a táblát modellező Cell típusú board[][] mátrix
létező koordinátái-e. Ügyelni kell arra, hogy hogyha szabálytalan lépés történik, akkor se a Player, se
a Hunter objektumok pozíciója ne változzon.
A játékos minden szabályos lépése után a Hunter objektumai úgy változtatják helyzetüket, hogy abba
az irányba lépnek egyet, amelyikben a Hunter és a Player x vagy y koordinátáinak különbsége
nagyobb. Ha egyenlő a távolság vízszintesen és függőlegesen is, akkor a függőleges tengelyen
mozog a Hunter.
A játék egyik lehetséges befejeződése, mikor valamelyik Hunter típusú objektum a mátrix egy olyan
eleme helyére kerülne, amelynek getGameCharacter() metódusa a Player típusú paraméterrel True
értéket ad vissza. Ezért az a vadászok új pozíciójához tartozó cellák karakterét tartalmazó változót
csak akkor szabad átállítani, miután ellenőriztük, hogy milyen karakter volt előtte azon a helyen. Ha
egy vadász egy mezőre lép a játékossal, akkor az isPlayerDead logikai változónak Igaz értéket adunk,
ami a játék végét és a számítógép győzelmét jelenti.
Egy másik lehetséges befejezés, mikor valamelyik a Player vagy az összes Hunter egy Mine típusú
karakterrel bíró cellára lép. Előbbi esetén a folyamat hasonló, mint a vadász és játékos találkozásánál,
ilyenkor is a számítógép győzelmével ér véget a játék. A játékos csak úgy nyerhet, ha minden vadász
aknára lép. Ha egy vadász (vagy játékos) aknára lép, akkor ő nem léphet többet, és megjelenítése az
aknával együtt eltűnik (meghívódik a cellára a setGameCharacter(null) metódus), és a 2-vel deklarált
NumberOfHunters-ből levonódik 1. A játék végét a NumberOfHunters 0-ra állása jelenti.
A játéktábla kirajzolása a sakktáblán levő cellák egyenkénti kirajzolásával jár. A cellák kirajzolásánál
meg kell határozni a cella pozícióját és méretét, és a rajta levő karaktert és színét.
A billentyűzetet figyelő eseménykezelő a megnyomott gomb alapján (ha az nyíl) hívja meg az
EscapeGameLogic move() metódusát. A cellák új állapotát a redraw() metódus meghívódása után, a
gombok háttérszínének cseréjével ismerheti meg a felhasználó.
A játék addig zajlik, míg az EscapeGameLogic objektum getIsPlayerDead() és getAreHuntersDead()
metódusai hamis értéket adnak vissza. Ellenkező esetben a játék végét felugró párbeszéd jelzi a
győztes kihirdetésével, majd automatikusan új játék indul az EscapeGameLogic példányosításával.
