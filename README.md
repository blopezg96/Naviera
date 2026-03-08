# Naviera
Software that emulates the behavior of a system that manages the operations of a shipping company.
There is no main method yet.

Package navio contains estado package where each the possible navio states are allocated, and also, there is an Interface called EstadoNavio. This Interface contains all estado´s class methods implemented.
Class navio manage all the crucial functions, implementing their own methods. 

tripulacion contains Enum RolTripulante and Tripulante is a class that creates instances with such particular properties. 

valueObject has two important classes: Posicion and Puerto. This two classes contains methods and properties that works with other classes, for example Viaje, EntradaLogbook and Navio. 

viaje contains all the information generated when Navio calls all its methods. Lets say, you cand find distance, state, departure and ariving positions, and dates. Also some anotations throuhgout the whole trip life-cycle. 

 

