import { Fisherman } from '../../hooks/useFishermen';
import RecollectionDetails from '../RecollectionDetails/RecollectionDetails';
import Image from 'next/image';
import styles from './FishermanDetails.module.css';

declare type Props = {
    content: Fisherman;
};

export default function FishermanDetails({ content }: Props): JSX.Element {
    return (
        <div className={styles.card}>
            <span>{content.name}</span>
            <Image alt={content.name} src={content.picture} width={200} height={200} />
            {content.recollections.map((each) => (
                <RecollectionDetails content={each} />
            ))}
        </div>
    );

}