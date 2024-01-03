import { useState } from "react";
import { useOutletContext } from "react-router-dom";
import MaterialSingleDescription from "./MaterialSingleDescription";
import MaterialSingleItem from "./MaterialSingleItem";
import DescriptionSubject from "./DescriptionSubject";
import useApi from "./Hooks/useApi";

function SingleMaterial() {
  const [displayDescription, setDisplayDescription] = useState(false);
  const [nameMaterials, dataSubjects, isLoadingSubjets] = useOutletContext();
  const { data, isLoading, erorr } = useApi("/api/przedmiot/zadanie/24");
  console.log(data);

  return (
    <div className="w-[90%] mx-auto mt-10">
      <MaterialSingleDescription
        setDisplayDescription={setDisplayDescription}
      />
      {displayDescription ? (
        <DescriptionSubject
          setDisplayDescription={setDisplayDescription}
          title={nameMaterials}
          data={dataSubjects}
          isLoading={isLoadingSubjets}
        />
      ) : (
        <>
          <MaterialSingleItem num={1} />
          <MaterialSingleItem num={2} />
          <MaterialSingleItem num={3} />
          <MaterialSingleItem num={4} />
          <MaterialSingleItem num={5} />
          <MaterialSingleItem num={6} />
          <MaterialSingleItem num={7} />
        </>
      )}
    </div>
  );
}
export default SingleMaterial;
