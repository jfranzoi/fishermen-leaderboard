'use client';

import { useFishermen } from '../../app/hooks/useFishermen';
import FishermanCard from '../../app/components/FishermanCard';
import styles from '../index.module.css';
import { useRouter } from 'next/router';

export default function Details() {
  const router = useRouter()
  const [fishermen, isLoading] = useFishermen();

  if(isLoading) return (<div>Lodading...</div>)

  return fishermen.length > 0 && (
    <main>
      <div className={styles.container}>
        <FishermanCard content={fishermen.find((x) => x.id === router.query.id )!!} />
      </div>
    </main>
  )
}
