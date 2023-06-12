import { Fisherman } from '../../models/Fisherman';
import Link from 'next/link';
import Image from 'next/image';
import styles from './FishermanCard.module.css';

declare type Props = {
    content: Fisherman;
};

export default function FishermanCard({ content }: Props): JSX.Element {
    return (
        <div className={styles.card}>
            <span><Link href={`/fisherman/${content.id}`}>{content.name}</Link> {content.amount} kg</span>
            <Image alt={content.name} src={content.picture} width={200} height={200} />
        </div>
    );

}