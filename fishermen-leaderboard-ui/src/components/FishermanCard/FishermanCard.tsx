import { Fisherman } from '@/models/Fisherman';
import Link from 'next/link';
import Image from 'next/image';
import styles from './FishermanCard.module.css';

declare type Props = {
    item: Fisherman;
    rank: number;
};

export default function FishermanCard({ item, rank }: Props): JSX.Element {
    const currentRankStyle = `r${rank}`
    return (
        <div className={styles.card}>
            <h2 className={`${styles.rank} ${styles[currentRankStyle]}`}>#{rank}</h2>
            <Image alt={item.name} src={item.picture} width={200} height={200} />
            <h3>{item.name}</h3>
            <span>{item.amount} kg</span>
        </div>
    );

}