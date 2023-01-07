import { FileHandler } from "./FileHandle.model";

export interface User{
    userId :number,
    nome : string,
    prenome : string,
    nomeArabe : string,
    prenomeArab : string,
    cin : string,
    profession : string,
    dateNaissance : string,
    typeCarte: string,
    userImage : FileHandler
}