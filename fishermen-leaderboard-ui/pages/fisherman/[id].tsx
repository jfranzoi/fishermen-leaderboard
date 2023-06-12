'use client';

import { useFishermanDetail } from '../../app/hooks/useFishermen';
import FishermanDetails from '../../app/components/FishermanDetails/FishermanDetails';
import styles from '../index.module.css';
import { useRouter } from 'next/router';

export default function Details() {
  const router = useRouter()
  const id = router.query.id as string;
  const [fisherman, isLoading] = useFishermanDetail(id);

  if (isLoading) return (<div>Lodading...</div>)

  return fisherman && (
    <main>
      <div className={styles.container}>
        <FishermanDetails content={fisherman} />
      </div>
    </main>
  )
}