import { Fisherman } from "../hooks/useFishermen";
import styles from './FishermanCard.module.css';

declare type Props = {
    content: Fisherman;
};

export default function FishermanCard({ content }: Props): JSX.Element {
    return (
        <div className={styles.card}>
            <span><a href={`/fisherman/${content.id}`}>{content.name}</a> {content.amount} kg</span>
            <img className={styles.picture} src={content.picture} />
        </div>
    );

}