:- use_module(library(clpfd)).
sudoku(StateRow) :-
                length(StateRow, 9),
                maplist(same_length(StateRow), StateRow),
                append(StateRow, Vs),
                Vs ins 1..9,
                maplist(all_distinct, StateRow),
                transpose(StateRow, Columns),
                maplist(all_distinct, Columns),
                StateRow = [As,Bs,Cs,Ds,Es,Fs,Gs,Hs,Is],
                part(As, Bs, Cs),
                part(Ds, Es, Fs),
                part(Gs, Hs, Is),
                maplist(labeling([ff]), StateRow),
                maplist(portray_clause, StateRow),
                open('example.txt', write, Stream),
                write(Stream, StateRow),
                close(Stream).
part([], [], []).
part([P1,P2,P3|Ps1], [P4,P5,P6|Ps2], [P7,P8,P9|Ps3]) :-
        all_distinct([P1,P2,P3,P4,P5,P6,P7,P8,P9]),
        part(Ps1, Ps2, Ps3).