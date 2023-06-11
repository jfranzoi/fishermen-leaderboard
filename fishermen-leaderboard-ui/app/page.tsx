'use client';

import { useFishermen } from './hooks/useFishermen';
import FishermanCard from './components/FishermanCard';
import styles from './page.module.css';

export default function Home() {
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
