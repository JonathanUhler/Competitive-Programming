import java.util.*;


public class Part1 {

	public static void main(String[] args) {
		String str = "wzsspbssbhshchmmrmprmrfrsfrrjhjphjjtppbfflqfqppdhdbhdhbbjgghgzgsgfsfhfhvvwcwhwwnppsbsggqnnvvtffpssjfjnnltntdtdddptprppjmmqssrlrplllfrrzggbmmlmnmtnnzddfdgfdfsstbstbbzcbbcjcvvfwwwzlzssglsgllnbnmbnmbbgmmppmwwsrrzqqvgqgnqnmmswwrnnbsnnbdbwwqnwqnwwzzqwzqzjqqwbqbccjcwwjlwjwppqfqjffwddrzrjzrjzzzhchjjlqqrggzvvnlvnvjvhvnvjvbbvdvldldbbwddtsscbbhccmbmbppbmmgjggcjjlrrwjwtjwwmffgddwvdvbdbvvhchmhmdmwwbjbjcbbcmbmmslmlslsjjrnrjrjpjcjgcjcjbbwsbsbfssjzsjsggcmcsswgssbbqpbqqdsdqqztzmzpmpbmbvbjjtrjtthwhbhnbhnbnssvpvsppgrrcwrrmqqhpphrpphchvhphqhbqbtttllsrlsrshrrmhmvvrmrnngbgnbnpbprrrftfjtjwwmrmvmcmvmrmwmssrccfbbhppjbpbwbqwbqbzzfmzmtttjbbvcvppmwwrbbzsbzbfzfdzzhrhzhvvsqvsqsnslljwllqlwllvccnttzhtzzpspqspqsqgsssvnsvshvsvqvnvlldbbfdfmmpbpgphhpqhpppchhbfbrfrllntnznrrhdrdhhdhjjgwwqhhfssjbjwjqqbffvvcrchccwmcwcllljtjrrqgrrdcrddvfvwvzzjqqcffgjgzzdhdwdssffjjhccggmtmltlhtthzhvhlvlddsfsbbtjbjcbjcjzcjjpfjfzzpnnbtbqtbqtqptpnplllbzbllhbllnqnttpddqvdqvqmmjnjggnnnhqqfcqcffqppdgpddvqddmhmrhrtrmrzmzhzpzlzqlqpqhphrpppjqqppzbzmmjgggqtggtjthhlclnclcnccmcppdcpddqrrfgrgbbhwhdhrrqtqpqjpqjpqjqqmcqmccnngqqqmtqtssnvnzvvpcpwcwwqcqqqfcqffsvsmvmttttmrrzssghgssjmsmbmvvwggggzhhfvdpgjmmvzbfjghqhrfbpmbvjzwvfmcthrqwdhghpwsspmhpqnmwhjzpnlzfnvhdnnrqwnvctbmjqzhqrpjlwrssdlwqzmsfrfzmgjhnwwnwczswnhsdbvqbmdlvntsdrhrjjcjjhpbblgwhjwdcdjtpvtmslwvncwdjbwzvbpzbvddvssnrhtshrcvnhqnpmjzfswqbbrztnwjcpflfbhnphfwmjvnvtswgfttgjcqcngmmwjlfsprwfcfwcmgrgbnqmzbtzbtbztngvrzpsnrzvhbsdjnzpwwzllgnfdrlwpmnrznqsqcmvnfbnhqjddvcjmtgbpbmsgqdqzflmlmqncmhwltrmdmgnwpfwddrdpfhsgsnggchzjhgpwrsmdzgjtrgmnprhbwbcbpzbdvvstfqcnqzbdjqpmrdbtgcthtclftghhmnrzrjqqsbndhpvmdpfpwdlhvmczvdfgvpqclssvlhqnhlcfnfbvtspdzmgzdctvpdcwchtqhpsgmmblspjdlvgblbpgrfrgnqqsphcsrgfsdmpqscbjmnqrfbcwfthdtswbzthpnvsfbntnbmmgpfzlqwhppvvdrmwbqzbgppbgsqmjfqtmntgwpnccthftwdmvwmnchlbjhsnmbhndczbrhhjpbvnjdzrcndbbmfwfwsjwfgbqhwhrsvlngsbhhlrdjzzbmjpsqhlpzwcsntjhlmngblspmsjrjwsjsrqwnrcwsmcsbmpjwrthbqhrschrmrppnnbmjbvjzlmzsrfdwqlfnfjljftjvzsqdwlhbblqcdlqjbprpcllhlhmwrbrlgfrcqshrtjpnmhljttdvpfnhdjqvjhhfczwvbzqgnzgljcfrbpgwnfhfchwzqmqqzpbcdpqmnbrppzblnnzqrfnmgtljwnfgzwvnjppdbdhbznvpgwhbdjjvlspgwgjsmfsvllpgwlfnnptmwnfsshjjvqzjwrqpvmsphpmftqdllqqdzcjwfvpftgvspprdwvvcnglmbpntghntdwpjvvsppgjvnbjgvtzchtqtwbddncsbrfcvrnvlggvwgmmnfzrswnzjrwthzmdsmzqmzsnrqsnslnhmfqljnnnzshqfqshrhhjmnhdgphctswdbhnrcgnzmmzqpjqbtdfhhltsmvvtntbgsznshhsghblhlhqpmdcfhmnfzvhgnfsfflcfwbfqzmccrjdpfvphtqbrdnzjfmfhbzqcpdnjdcgwprvchlzrcvrghgjqncjvnndbcshntrfsbsnmjlhclnzpfdgztflcpwqpnvlscfndwqzfvcmpgfncszpmwcsrdbrrhdjvmthslfvmlgpqhlgwhqnjljcvhswbsbqfrfhvzwjvdmhzsgbmbmfnbpclqdwhvrlpppszptjvwtvdmfltfqqgjttdggcvllblnnhjqnjzhvpgpzzpzwbpjqbthnjjlmsjgjzqwnjlqrcdmmvsldtcrzqdrcmwqhnhfghdlmzwcspgmlpzhbdsmlwlqnhhvcvdfzmvfwpbfmjtdllprfqzzjpbrshdzgspsrlrwrhdpmznzzqngwrzqpmtwgbsswrnnnfctjhbcftnslsqwjvmfwfdvfqcnsvfsvgstgbzpmljjtlvtnfsdzpvcgbjqwgbgzqbjfgltqvnhffflsbjzfqfrfbssrvvwqvqptmhrbgllqjwptrzgvqgccsrvbgtvmzfzlmtqrgfwhzddsptclbhjlwqfntvjqdvcddnffmbtqrnsvtmlvljcqdsrpggcmqvmmlzwwbgznhblwzjdppvtqzjvcmtfzhdzjplrdbrfrgzpldvnsgqlbwbmfvrbgwzmjmmqdfgwtbgtzmdqnvqbwvcfjhddqvnjtjlhhjnltbtqqvblwlmglrqcrcfjvdntrnqzzbmrjqglmrdcjgnshcghtprjqqsdrmgdnzmzcfqqdtjtrqgtqtgrpmmgzjtcrznmqccjbdpbvrnnbmbzvgdcnrczbctbrsrrqrjnfcdpzlnngwvcdtbbgssvhpptntqdzhcqtlpvzjbfgzggrgrcgtdfjbwdcrpztnfcdbscnlmqmwcbmtnddgbmhwsgvcfdcmhlsvtnqtmrnsjzhppgwvzlmhwwmpfjzrfbhsgntzrdhwswrnfmmmczqrvdrqnhgrvqbdddhglwsftsljvgbnjqfwfzsspdqvgsnlgfsfpvdrjhzcldtrmjjrmdhvvfrjldhhtqnvsvlldjpjbpwstfsmrpmbqbnnpvqtbgjvblthbmwqtfcfgnjscvtbvlqcmlhffpzgjzfscsqwnhrjhvbrrzwqvbjwtwhtqsdbssfgncppnsfgfltdcbjqjzqqtprsbvjzhmchnltvmbsvpvhgzhfhbrnttsqbcmwpdnwqqgdrjrdwdhtzwsmcdffqgsddvbzfjhtfhtnfdbfrwmdtcqshfjrcpswzcptgwgmctpmzjdbqlmqwthmnfplmctpsslcsdtqpqhjtmjdnmnqnjgchwstsmtpvsmgpsbfgwqnzhrdgdvcdlcldfcmjvsdldgbmhltjhczffwmzqssnhfnwftfgpshntjbpjdffjpcmcpwhclrrwqcqzmntjglzgcfrplfpvprtpvpjdlcrfwrtrzdzmhsrsmdcpqqrqgvfpdbmzbzqdfhpplmgfrdghclbclgswvwhhdvcpmpzflpffmptcrwglftztccrpbrvmpnqmqdgjgrrlbtqtvgcjpljttwtdslqjqlsdpblgrqbrtbmtblfbqtbvsqhpqzpqfhjqpmjrmcvqmsbbpjpdncgnjftclbltwszrrfzqbjcdtphcvpmbhppvwjwlprgmghrjzzgnvzlvghnjbzqjpdgzfsnjchpbzqdzpsjmsrvvqwjcpwznlpbjldlrdfqtrzhqzcnpjqbbbf";
		for (int i = 0; i <= str.length() - 4; i += 1) {
			String substr = str.substring(i, i + 4);
			Set<Character> set = new HashSet<>();
			for (char c : substr.toCharArray())
				set.add(c);
			System.out.println(substr);
			if (set.size() == 4) {
				System.out.println("ANSWER: " + (i + 4));
				return;
			}
		}
	}

}