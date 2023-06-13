import { Recollection } from '@/models/Recollection';
import Date from '@/components/date';
import Image from 'next/image';
import styles from './RecollectionDetails.module.css';

declare type Props = {
    item: Recollection;
}

export default function RecollectionDetails({ item }: Props): JSX.Element {
    return (
        <div>
            <h3>{item.amount} kg</h3> <Date text={item.date} />
            <Image className={styles.picture} alt={`${item.amount} kg`} src={item.picture} width={200} height={300}/>
        </div>
    );
}