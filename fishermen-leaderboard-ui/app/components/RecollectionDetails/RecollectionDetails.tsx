import { Recollection } from '../../models/Recollection';
import Image from 'next/image';
import styles from './RecollectionDetails.module.css';

declare type Props = {
    content: Recollection;
}

export default function RecollectionDetails({ content }: Props): JSX.Element {
    return (
        <div>
            {content.amount} kg at {content.date.toString()}
            <div className={styles.picture}>
                <Image alt={`${content.amount} kg`} src={content.picture} width={100} height={100} />
            </div>
        </div>
    );
}