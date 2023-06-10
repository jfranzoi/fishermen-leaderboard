import { Fisherman } from "../hooks/useFishermen";
declare type Props = {
    content: Fisherman;
};

export default function FishermanCard({ content }: Props): JSX.Element {
    return (
        <div>
            <span>{content.name}: {content.amount} kg</span>
        </div>
    );

}