import { Fisherman } from '../../hooks/useFishermen';
import RecollectionDetails from '../RecollectionDetails/RecollectionDetails';
import styles from './FishermanDetails.module.css';

declare type Props = {
    content: Fisherman;
};

export default function FishermanDetails({ content }: Props): JSX.Element {
    return (
        <div className={styles.card}>
            <span>{content.name}</span>
            <img className={styles.picture} src={content.picture} />
            {content.recollections.map((each) => (
                <RecollectionDetails content={each} />
            ))}
        </div>
    );

}