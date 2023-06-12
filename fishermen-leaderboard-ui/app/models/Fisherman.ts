import { Recollection } from './Recollection';

export type Fisherman = {
    id: string;
    name: string;
    amount: number;
    picture: string;
    recollections: Recollection[];
}