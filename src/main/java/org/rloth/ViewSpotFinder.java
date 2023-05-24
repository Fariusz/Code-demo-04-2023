package org.rloth;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rloth.exceptions.ProcessingFileException;
import org.rloth.models.Element;
import org.rloth.models.Mesh;
import org.rloth.models.Value;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ViewSpotFinder {

    private ViewSpotFinder() {
    }

    public static List<Element> findViewSpots(String meshFilePath, int numberOfViewSpots) throws ProcessingFileException {

        Set<Integer> nodeIds = new LinkedHashSet<>();

        return getViewSpotsOrderedList(readMeshFromJsonFile(meshFilePath))
                .stream()
                .filter(element -> {
                    if (element.getNodeIDs()
                            .stream()
                            .anyMatch(nodeIds::contains)) {
                        return false;
                    } else {
                        nodeIds.addAll(element.getNodeIDs());
                        return true;
                    }
                })
                .limit(numberOfViewSpots)
                .toList();
    }

    private static List<Element> getViewSpotsOrderedList(Mesh mesh) {
        List<Value> values = mesh.getValues()
                .stream()
                .sorted(Comparator.comparing(Value::getValue).reversed())
                .toList();

        Map<Integer, Element> elementMap = mesh.getElements()
                .stream()
                .collect(Collectors.toMap(Element::getId, Function.identity()));

        return values.stream()
                .map(value -> elementMap.get(value.getElementId()))
                .toList();
    }

    private static Mesh readMeshFromJsonFile(String filePath) throws ProcessingFileException {
        try {
            return new ObjectMapper().readValue(new File(filePath), Mesh.class);
        } catch (JsonParseException e) {
            throw new ProcessingFileException("Failed parsing " + e.getMessage());
        } catch (JsonMappingException e) {
            throw new ProcessingFileException("Failed mapping " + e.getMessage());
        } catch (IOException e) {
            throw new ProcessingFileException("Failed reading file " + e.getMessage());
        } catch (Exception e) {
            throw new ProcessingFileException("Something went wrong " + e.getMessage());
        }
    }
}
