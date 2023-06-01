:- use_module(library(clpfd)).

% Define the n_queens predicate
n_queens(N, Qs) :-
        length(Qs, N),
        Qs ins 1..N,
        safe_queens(Qs),
        labeling([], Qs).

% Define the safe_queens predicate
safe_queens([]).
safe_queens([Q|Qs]) :- 
        safe_queens(Qs, Q, 1), 
        safe_queens(Qs).

% Define the safe_queens helper predicate
safe_queens([], _, _).
safe_queens([Q|Qs], Q0, D0) :-
        Q0 #\= Q,
        abs(Q0 - Q) #\= D0,
        D1 #= D0 + 1,
        safe_queens(Qs, Q0, D1).