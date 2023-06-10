import { Fisherman } from "../hooks/useFishermen";
import styles from '../page.module.css';

declare type Props = {
    content: Fisherman;
};

export default function FishermanCard({ content }: Props): JSX.Element {
    return (
        <div className={styles.card}>
            <span>{content.name}: {content.amount} kg</span>
        </div>
    );

}