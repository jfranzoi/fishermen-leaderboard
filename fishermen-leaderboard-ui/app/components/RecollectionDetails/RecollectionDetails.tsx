import { Recollection } from "@/app/hooks/useFishermen";

declare type Props = {
    content: Recollection;
}

export default function RecollectionDetails({ content }: Props): JSX.Element {
    return (
        <div>{content.amount} kg at {content.date.toString()}</div>
    );
}