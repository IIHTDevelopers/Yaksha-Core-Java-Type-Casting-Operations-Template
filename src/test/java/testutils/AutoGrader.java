package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;

public class AutoGrader {

	// Test for correct usage of type casting (both implicit and explicit)
	public boolean testTypeCasting(String filePath) throws IOException {
		System.out.println("Starting testTypeCasting with file: " + filePath);

		File participantFile = new File(filePath); // Path to participant's file
		if (!participantFile.exists()) {
			System.out.println("File does not exist at path: " + filePath);
			return false;
		}

		FileInputStream fileInputStream = new FileInputStream(participantFile);
		JavaParser javaParser = new JavaParser();
		CompilationUnit cu;
		try {
			cu = javaParser.parse(fileInputStream).getResult()
					.orElseThrow(() -> new IOException("Failed to parse the Java file"));
		} catch (IOException e) {
			System.out.println("Error parsing the file: " + e.getMessage());
			throw e;
		}

		System.out.println("Parsed the Java file successfully.");

		boolean hasExplicitCasting = false;
		boolean hasImplicitCasting = false;

		// Log the parsed explicit cast expressions
		System.out.println("------ Explicit Cast Declarations ------");
		for (CastExpr cast : cu.findAll(CastExpr.class)) {
			hasExplicitCasting = true;
			System.out.println("✓ Found explicit casting: " + cast.getExpression() + " to " + cast.getType());
		}

		// Log the parsed implicit casting (assignments that involve type widening)
		System.out.println("------ Implicit Cast Declarations ------");
		for (ExpressionStmt expr : cu.findAll(ExpressionStmt.class)) {
			if (expr.toString().contains("double") && expr.toString().contains("int")) {
				hasImplicitCasting = true;
				System.out.println("✓ Found implicit casting (int to double).");
			}
		}

		// Check if both explicit and implicit casting are present
		System.out.println("Has explicit casting: " + hasExplicitCasting);
		System.out.println("Has implicit casting: " + hasImplicitCasting);

		boolean result = hasExplicitCasting && hasImplicitCasting;
		System.out.println("Test result: " + result);

		return result;
	}

}
