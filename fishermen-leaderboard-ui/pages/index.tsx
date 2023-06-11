'use client';

import { useFishermen } from '../app/hooks/useFishermen';
import FishermanCard from '../app/components/FishermanCard';
import styles from './index.module.css';

export default function Index() {
  const [fishermen, isLoading, loadNext] = useFishermen();

  return (
    <main>
      <div className={styles.container}>
        <h1 className={styles.header}>Top Scorer</h1>
        {fishermen.map((each) => {
          return (
            <FishermanCard key={each.id} content={each} />
          );
        })}
        <button className={styles.submitBtn} onClick={loadNext} disabled={isLoading}>
          {!isLoading ? 'Load More' : '...'}
        </button>
      </div>
    </main>
  )
}
