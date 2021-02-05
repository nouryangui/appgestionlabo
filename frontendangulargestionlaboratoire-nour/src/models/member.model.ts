import { Publication } from './publication.model';

/*TODO: complete the rest of properties*/
export interface Member {
  id: number;
  cin: number;
  firstName:string;
  lastName:string;

  birthDate: string;
  email:string;
  cv: string;
  type: string;
  diplome:string;
  dateInscription:string;
  grade:string
  etablissement:string
  image:FormData;
  publications:Publication[];
  teacher:Member;
  username:string;
  password:number;
  students:Member[];
}
